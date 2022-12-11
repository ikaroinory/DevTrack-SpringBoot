package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.NotificationDAO;
import cn.auroralab.devtrack.dao.ProjectDAO;
import cn.auroralab.devtrack.dao.RoleDAO;
import cn.auroralab.devtrack.dto.NotificationDTO;
import cn.auroralab.devtrack.enumeration.NotificationType;
import cn.auroralab.devtrack.exception.notification.NotificationNotFoundException;
import cn.auroralab.devtrack.exception.project.ProjectNotFoundException;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.system.UnknownException;
import cn.auroralab.devtrack.po.Notification;
import cn.auroralab.devtrack.po.Project;
import cn.auroralab.devtrack.po.Role;
import cn.auroralab.devtrack.service.NotificationService;
import cn.auroralab.devtrack.util.BitstreamGenerator;
import cn.auroralab.devtrack.util.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationDAO notificationDAO;
    private final ProjectDAO projectDAO;
    private final RoleDAO roleDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO, ProjectDAO projectDAO, RoleDAO roleDAO) {
        this.notificationDAO = notificationDAO;
        this.projectDAO = projectDAO;
        this.roleDAO = roleDAO;
    }

    public List<NotificationDTO> getList(String userUUID)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(userUUID);

        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Notification.RECIPIENT, userUUID)
                .isNull(Notification.READ_TIME);

        List<Notification> list = notificationDAO.selectList(queryWrapper);

        return list.stream().map(NotificationDTO::new).toList();
    }

    public void newProjectInvitations(String requesterUUID, String projectUUID, List<String> recipientUUIDList)
            throws RequiredParametersIsEmptyException, ProjectNotFoundException, PermissionDeniedException {
        Validator.notEmpty(requesterUUID);
        Validator.notEmpty(recipientUUIDList);

        Project project = projectDAO.selectById(projectUUID);

        if (project == null)
            throw new ProjectNotFoundException();

        Role role = roleDAO.getRoleByUserInProject(requesterUUID, projectUUID);

        if (role == null || !role.getInviteMember())
            throw new PermissionDeniedException();

        String context = "项目 " + project.getName() + "（UUID：" + project.getUuid() + "） 邀请您加入。";

        Notification notification = new Notification();
        notification.setUuid(BitstreamGenerator.parseUUID());
        notification.setType(NotificationType.PROJECT_INVITATION);
        notification.setTime(LocalDateTime.now());
        notification.setContext(context);
        notification.setParamUUID(projectUUID);

        recipientUUIDList.forEach(recipientUUID -> {
            notification.setRecipient(recipientUUID);
            notificationDAO.insert(notification);
        });
    }

    public void read(String notificationUUID)
            throws RequiredParametersIsEmptyException, NotificationNotFoundException, UnknownException {
        Validator.notEmpty(notificationUUID);

        Notification notification = notificationDAO.selectById(notificationUUID);

        if (notificationUUID == null)
            throw new NotificationNotFoundException();

        Notification newNotification = new Notification();
        newNotification.setUuid(notification.getUuid());
        newNotification.setReadTime(LocalDateTime.now());

        int i = notificationDAO.updateById(newNotification);

        if (i != 1)
            throw new UnknownException();
    }
}
