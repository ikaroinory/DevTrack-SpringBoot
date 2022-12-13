package cn.auroralab.devtrack.exception.notification;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class NotificationNotFoundException extends ResponseException {
    public NotificationNotFoundException() {
        super(
                "Notification not found.",
                StatusCode.NOTIFICATION_NOT_FOUND
        );
    }
}
