package cn.auroralab.devtrack.dto;

import cn.auroralab.devtrack.enumeration.NotificationType;
import cn.auroralab.devtrack.po.Notification;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationDTO {
    private String uuid;
    private NotificationType type;
    private LocalDateTime time;
    private String context;
    private String paramUUID;

    public NotificationDTO(Notification notification) {
        uuid = notification.getUuid();
        type = notification.getType();
        time = notification.getTime();
        context = notification.getContext();
        paramUUID = notification.getParamUUID();
    }
}
