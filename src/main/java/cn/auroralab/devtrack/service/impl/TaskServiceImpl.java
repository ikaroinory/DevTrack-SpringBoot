package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.RoleDAO;
import cn.auroralab.devtrack.dao.TaskDAO;
import cn.auroralab.devtrack.dao.TaskMemberDAO;
import cn.auroralab.devtrack.dto.HeatMapData;
import cn.auroralab.devtrack.dto.TaskDTO;
import cn.auroralab.devtrack.enumeration.Priority;
import cn.auroralab.devtrack.enumeration.SourceOfDemand;
import cn.auroralab.devtrack.enumeration.TaskType;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.form.NewTaskForm;
import cn.auroralab.devtrack.po.Role;
import cn.auroralab.devtrack.po.Task;
import cn.auroralab.devtrack.service.TaskService;
import cn.auroralab.devtrack.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDAO taskDAO;
    private final TaskMemberDAO taskMemberDAO;
    private final RoleDAO roleDAO;

    public TaskServiceImpl(TaskDAO taskDAO, TaskMemberDAO taskMemberDAO, RoleDAO roleDAO) {
        this.taskDAO = taskDAO;
        this.taskMemberDAO = taskMemberDAO;
        this.roleDAO = roleDAO;
    }

    public void newTask(String creatorUUID, NewTaskForm form)
            throws RequiredParametersIsEmptyException, PermissionDeniedException {
        Validator.notEmpty(creatorUUID, form.getFromProject(), form.getPrincipal());

        Role role = roleDAO.getRoleByUserInProject(creatorUUID, form.getFromProject());

        if (role == null || !role.getCreateTask())
            throw new PermissionDeniedException();

        Task task = new Task();
        task.setUuid(BitstreamGenerator.parseUUID());
        task.setFromProject(form.getFromProject());
        task.setType(Parseable.parse(form.getType(), TaskType.class, TaskType.UNKNOWN));
        task.setTitle(form.getTitle());
        task.setCreator(creatorUUID);
        task.setPrincipal(form.getPrincipal());
        task.setPriority(Parseable.parse(form.getPriority(), Priority.class, Priority.UNKNOWN));
        task.setSourceOfDemand(Parseable.parse(form.getSourceOfDemand(), SourceOfDemand.class, SourceOfDemand.UNKNOWN));
        task.setDescription(form.getDescription());
        task.setCreationTime(LocalDateTime.now());
        task.setStartTime(form.getStartTime());
        task.setDeadline(form.getDeadline());

        taskDAO.insert(task);

        if (!form.getMembers().isEmpty())
            taskMemberDAO.newRecords(form.getMembers(), task.getUuid());
    }

    public PageInformation<TaskDTO> getTaskList(String projectUUID, int pageNum, int pageSize)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(projectUUID);

        PageInfo<TaskDTO> pageInfo;
        try (Page<Object> ignored = PageHelper.startPage(pageNum, pageSize)) {
            List<TaskDTO> list = taskDAO.getTaskList(projectUUID);
            pageInfo = new PageInfo<>(list);
        }
        return PaginationUtils.parsePageInformation(pageInfo);
    }

    public List<HeatMapData> getTaskCountFinishedInThePastYear(String userUUID)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(userUUID);

        return taskDAO.getTaskCountFinishedInThePastYear(userUUID);
    }
}
