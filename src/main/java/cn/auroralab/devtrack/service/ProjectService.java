package cn.auroralab.devtrack.service;

import cn.auroralab.devtrack.dto.ProjectDTO;
import cn.auroralab.devtrack.exception.project.ProjectNotFoundException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.form.UpdateProjectInformationForm;
import cn.auroralab.devtrack.po.Project;
import cn.auroralab.devtrack.util.PageInformation;

public interface ProjectService {
    /**
     * 创建项目。
     *
     * @param project 项目对象。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    void create(Project project)
            throws RequiredParametersIsEmptyException;

    /**
     * 更新项目信息。
     *
     * @author Guanyu Hu
     * @since 2022-11-18
     */
    void update(UpdateProjectInformationForm form)
            throws RequiredParametersIsEmptyException, ProjectNotFoundException;

    /**
     * 获取项目信息。
     *
     * @param projectUUID 项目UUID。
     * @author Guanyu Hu
     * @since 2022-11-18
     */
    ProjectDTO get(String projectUUID)
            throws RequiredParametersIsEmptyException;

    /**
     * 获取一页用户的项目信息。
     *
     * @param userUUID 用户uuid。
     * @param pageNum  页码。
     * @param pageSize 每页大小。
     * @author Guanyu Hu
     * @since 2022-11-22
     */
    PageInformation<ProjectDTO> getUserProjectList(String userUUID, int pageNum, int pageSize)
            throws RequiredParametersIsEmptyException;
}
