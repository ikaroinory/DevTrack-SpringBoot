package cn.auroralab.devtrack.service;

import cn.auroralab.devtrack.dto.NotificationDTO;
import cn.auroralab.devtrack.exception.notification.NotificationNotFoundException;
import cn.auroralab.devtrack.exception.project.ProjectNotFoundException;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.system.UnknownException;

import java.util.List;

public interface NotificationService {
    /**
     * 获取用户通知列表。
     *
     * @param userUUID 用户UUID。
     * @author Guanyu Hu
     * @since 2022-12-11
     */
    List<NotificationDTO> getList(String userUUID)
            throws RequiredParametersIsEmptyException;

    /**
     * 创建一条项目邀请通知。
     *
     * @param requesterUUID
     * @param projectUUID       项目UUID。
     * @param recipientUUIDList 收件人UUID列表。
     * @author Guanyu Hu
     * @since 2022-12-11
     */
    void newProjectInvitations(String requesterUUID, String projectUUID, List<String> recipientUUIDList)
            throws RequiredParametersIsEmptyException, ProjectNotFoundException, PermissionDeniedException;

    /**
     * 已读通知。
     *
     * @param notificationUUID 通知UUID。
     * @author Guanyu Hu
     * @since 2022-12-12
     */
    void read(String notificationUUID)
            throws RequiredParametersIsEmptyException, NotificationNotFoundException, UnknownException;
}
