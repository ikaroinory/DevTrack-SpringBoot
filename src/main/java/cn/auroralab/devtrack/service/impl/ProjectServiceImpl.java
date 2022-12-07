package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.ProjectDAO;
import cn.auroralab.devtrack.dto.ProjectDTO;
import cn.auroralab.devtrack.exception.project.ProjectNotFoundException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.form.UpdateProjectInformationForm;
import cn.auroralab.devtrack.po.Project;
import cn.auroralab.devtrack.service.ProjectService;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.util.PaginationUtils;
import cn.auroralab.devtrack.util.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDAO projectDAO;

    public ProjectServiceImpl(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public void create(Project project)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(
                project.getUuid(), project.getName(),
                project.getCreator(), project.getPrincipal(),
                project.getDefaultRole()
        );

        projectDAO.insert(project);
    }

    public void update(UpdateProjectInformationForm form)
            throws RequiredParametersIsEmptyException, ProjectNotFoundException {
        Validator.notEmpty(form.getProjectUUID());

        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Project.UUID, form.getProjectUUID());

        if (projectDAO.selectOne(queryWrapper) == null)
            throw new ProjectNotFoundException();

        Project project = new Project();
        project.setName(form.getName());
        project.setPrincipal(form.getPrincipalUUID());
        project.setDescription(form.getDescription());
        project.setStartTime(form.getStartTime());

        projectDAO.update(project, queryWrapper);
    }

    public ProjectDTO get(String projectUUID)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(projectUUID);

        return projectDAO.get(projectUUID);
    }

    public PageInformation<ProjectDTO> getUserProjectList(String userUUID, int pageNum, int pageSize)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(userUUID);

        PageInfo<ProjectDTO> pageInfo;
        try (Page<Object> ignored = PageHelper.startPage(pageNum, pageSize)) {
            List<ProjectDTO> list = projectDAO.getList(userUUID);
            pageInfo = new PageInfo<>(list);
        }
        return PaginationUtils.parsePageInformation(pageInfo);
    }
}
