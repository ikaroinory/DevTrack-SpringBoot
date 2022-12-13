package cn.auroralab.devtrack.dto;

import cn.auroralab.devtrack.enumeration.NotificationType;
import cn.auroralab.devtrack.po.Notification;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationDTO {
    private String uuid;
    private NotificationType type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    private String title;
    private String context;
    private String paramUUID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handlingTime;

    public NotificationDTO(Notification notification) {
        uuid = notification.getUuid();
        type = notification.getType();
        time = notification.getTime();
        title = notification.getTitle();
        context = notification.getContext();
        paramUUID = notification.getParamUUID();
        handlingTime = notification.getHandlingTime();
    }
}
