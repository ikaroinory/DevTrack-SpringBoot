package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.dto.NotificationDTO;
import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.service.NotificationService;
import cn.auroralab.devtrack.util.JwtUtils;
import cn.auroralab.devtrack.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) { this.notificationService = notificationService; }

    @GetMapping("/get")
    public ResponseVO<List<NotificationDTO>> get(@RequestHeader(value = "Authorization") String authorization) {
        String userUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;
        List<NotificationDTO> list = null;

        try {
            list = notificationService.getList(userUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, list);
    }

    @PostMapping("/delete")
    public StatusCode delete(String notificationUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;
        try {
            notificationService.delete(notificationUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }
}
