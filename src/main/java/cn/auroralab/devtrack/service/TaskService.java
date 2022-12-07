package cn.auroralab.devtrack.service;

import cn.auroralab.devtrack.dto.HeatMapData;
import cn.auroralab.devtrack.dto.TaskDTO;
import cn.auroralab.devtrack.dto.TaskMemberDTO;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.task.TaskNotFoundException;
import cn.auroralab.devtrack.form.NewTaskForm;
import cn.auroralab.devtrack.util.PageInformation;

import java.util.List;

public interface TaskService {
    /**
     * 创建任务。
     *
     * @param creatorUUID 创建人UUID。
     * @param form        创建任务表单。
     * @author Guanyu Hu
     * @since 2022-11-09
     */
    void newTask(String creatorUUID, NewTaskForm form)
            throws RequiredParametersIsEmptyException, PermissionDeniedException;

    /**
     * 获取一页项目任务信息。
     *
     * @param projectUUID 项目UUID。
     * @param pageNum     页码。
     * @param pageSize    每页大小。
     * @author Guanyu Hu
     * @since 2022-11-22
     */
    PageInformation<TaskDTO> getTaskList(String projectUUID, int pageNum, int pageSize)
            throws RequiredParametersIsEmptyException;

    /**
     * 获取任务成员列表。
     *
     * @param taskUUID 任务UUID。
     * @author Guanyu Hu
     * @since 2022-12-07
     */
    List<TaskMemberDTO> getTaskMemberList(String taskUUID)
            throws RequiredParametersIsEmptyException;

    /**
     * 获取用户过去一年完成的任务数量。
     *
     * @param userUUID 用户UUID。
     * @author Guanyu Hu
     * @since 2022-11-08
     */
    List<HeatMapData> getTaskCountFinishedInThePastYear(String userUUID)
            throws RequiredParametersIsEmptyException;

    /**
     * 修改任务标题。
     *
     * @param requesterUUID 请求人UUID。
     * @param taskUUID      任务UUID。
     * @param title         新任务标题。
     * @author Guanyu Hu
     * @since 2022-12-07
     */
    void updateTitle(String requesterUUID, String taskUUID, String title)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException;

    /**
     * 修改任务负责人。
     *
     * @param requesterUUID 请求人UUID。
     * @param taskUUID      任务UUID。
     * @param principalUUID 新负责人UUID。
     * @author Guanyu Hu
     * @since 2022-12-07
     */
    void updatePrincipal(String requesterUUID, String taskUUID, String principalUUID)
            throws RequiredParametersIsEmptyException, TaskNotFoundException, PermissionDeniedException;
}
