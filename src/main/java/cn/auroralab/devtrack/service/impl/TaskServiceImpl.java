package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.ProjectDAO;
import cn.auroralab.devtrack.dao.RoleDAO;
import cn.auroralab.devtrack.dao.TaskDAO;
import cn.auroralab.devtrack.dao.TaskMemberDAO;
import cn.auroralab.devtrack.dto.HeatMapData;
import cn.auroralab.devtrack.dto.TaskDTO;
import cn.auroralab.devtrack.dto.TaskMemberDTO;
import cn.auroralab.devtrack.dto.TaskStatisticsDTO;
import cn.auroralab.devtrack.enumeration.Priority;
import cn.auroralab.devtrack.enumeration.SourceOfDemand;
import cn.auroralab.devtrack.enumeration.TaskType;
import cn.auroralab.devtrack.exception.project.ProjectNotFoundException;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.task.TaskNotFoundException;
import cn.auroralab.devtrack.form.NewTaskForm;
import cn.auroralab.devtrack.po.Project;
import cn.auroralab.devtrack.po.Role;
import cn.auroralab.devtrack.po.Task;
import cn.auroralab.devtrack.po.TaskMember;
import cn.auroralab.devtrack.service.TaskService;
import cn.auroralab.devtrack.util.*;
import cn.auroralab.devtrack.vo.TaskStatisticsVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDAO taskDAO;
    private final TaskMemberDAO taskMemberDAO;
    private final RoleDAO roleDAO;
    private final ProjectDAO projectDAO;

    public TaskServiceImpl(TaskDAO taskDAO, TaskMemberDAO taskMemberDAO, RoleDAO roleDAO, ProjectDAO projectDAO) {
        this.taskDAO = taskDAO;
        this.taskMemberDAO = taskMemberDAO;
        this.roleDAO = roleDAO;
        this.projectDAO = projectDAO;
    }

    private void updateTaskValidator(String userUUID, String taskUUID)
            throws TaskNotFoundException, PermissionDeniedException {
        Task task = taskDAO.selectById(taskUUID);

        if (task == null)
            throw new TaskNotFoundException();

        Role role = roleDAO.getRoleByUserInProject(userUUID, task.getFromProject());

        if (role == null || !role.getUpdateTask())
            throw new PermissionDeniedException();
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
            taskMemberDAO.newRecords(task.getUuid(), form.getMembers());
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

    public List<TaskMemberDTO> getTaskMemberList(String taskUUID)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(taskUUID);

        return taskMemberDAO.getTaskMemberList(taskUUID);
    }

    public List<HeatMapData> getTaskCountFinishedInThePastYear(String userUUID)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(userUUID);

        return taskDAO.getTaskCountFinishedInThePastYear(userUUID);
    }

    public void updateTitle(String requesterUUID, String taskUUID, String title)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID, title);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setTitle(title);

        taskDAO.updateById(task);
    }

    public void updatePrincipal(String requesterUUID, String taskUUID, String principalUUID)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID, principalUUID);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setPrincipal(principalUUID);

        taskDAO.updateById(task);
    }

    public void updateStartTime(String requesterUUID, String taskUUID, LocalDateTime startTime)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);
        Validator.notNull(startTime);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setStartTime(startTime);

        taskDAO.updateById(task);
    }

    public void updateDeadline(String requesterUUID, String taskUUID, LocalDateTime deadline)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);
        Validator.notNull(deadline);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setDeadline(deadline);

        taskDAO.updateById(task);
    }

    public void updateTaskType(String requesterUUID, String taskUUID, TaskType taskType)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);
        Validator.notNull(taskType);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setType(taskType);

        taskDAO.updateById(task);
    }

    public void updatePriority(String requesterUUID, String taskUUID, Priority priority)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);
        Validator.notNull(priority);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setPriority(priority);

        taskDAO.updateById(task);
    }

    public void updateSourceOfDemand(String requesterUUID, String taskUUID, SourceOfDemand sourceOfDemand)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);
        Validator.notNull(sourceOfDemand);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setSourceOfDemand(sourceOfDemand);

        taskDAO.updateById(task);
    }

    public void updateDescription(String requesterUUID, String taskUUID, String description)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);
        Validator.notNull(description);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setDescription(description);

        taskDAO.updateById(task);
    }

    public void updateMembers(String requesterUUID, String taskUUID, List<String> memberUUIDList)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);

        updateTaskValidator(requesterUUID, taskUUID);

        QueryWrapper<TaskMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TaskMember.TASK_UUID, taskUUID);

        taskMemberDAO.delete(queryWrapper);

        if (!memberUUIDList.isEmpty())
            taskMemberDAO.newRecords(taskUUID, memberUUIDList);
    }

    public void finish(String requesterUUID, String taskUUID, boolean finished)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);

        updateTaskValidator(requesterUUID, taskUUID);

        Task task = new Task();
        task.setUuid(taskUUID);
        if (finished)
            task.setFinishTime(LocalDateTime.now());
        else
            task.setFinishTime(null);

        taskDAO.updateById(task);
    }

    public void delete(String requesterUUID, String taskUUID)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID, taskUUID);

        updateTaskValidator(requesterUUID, taskUUID);

        QueryWrapper<TaskMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TaskMember.TASK_UUID, taskUUID);
        taskMemberDAO.delete(queryWrapper);

        Task task = new Task();
        task.setUuid(taskUUID);
        task.setDeleted(true);
        task.setDeleteTime(LocalDateTime.now());

        taskDAO.updateById(task);
    }

    public TaskStatisticsVO getTaskStatistics(String projectUUID)
            throws RequiredParametersIsEmptyException, ProjectNotFoundException {
        Validator.notEmpty(projectUUID);

        List<TaskStatisticsDTO> list = taskDAO.getTaskStatistics(projectUUID);

        Project project = projectDAO.selectById(projectUUID);

        if (project == null)
            throw new ProjectNotFoundException();

        TaskStatisticsVO statisticsVO = new TaskStatisticsVO();

        LocalDate now = LocalDate.now();
        int count = list.size();
        int curPointer = 0;
        for (LocalDate date = project.getCreationTime().toLocalDate(); now.isAfter(date) || now.isEqual(date); date = date.plusDays(1)) {
            if (curPointer < count && LocalDate.parse(list.get(curPointer).getDate()).isEqual(date)) {
                statisticsVO.getDateList().add(list.get(curPointer).getDate());
                statisticsVO.getCreationList().add(list.get(curPointer).creation);
                statisticsVO.getCompletionList().add(list.get(curPointer).completion);
                curPointer++;
            } else {
                statisticsVO.getDateList().add(date.toString());
                statisticsVO.getCreationList().add(null);
                statisticsVO.getCompletionList().add(null);
            }
        }

        return statisticsVO;
    }
}
