package cn.auroralab.devtrack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    /**
     * 任务UUID。
     */
    private String taskUUID;
    /**
     * 所属项目UUID。
     */
    private String fromProjectUUID;
    /**
     * 任务名。
     */
    private String taskName;
    /**
     * 任务类型。
     */
    private int taskType;
    /**
     * 创建人UUID。
     */
    private String creatorUUID;
    /**
     * 创建人用户名。
     */
    private String creatorUsername;
    /**
     * 创建人昵称。
     */
    private String creatorNickname;
    /**
     * 创建人头像。
     */
    private byte[] creatorAvatar;
    /**
     * 负责人UUID。
     */
    private String principalUUID;
    /**
     * 负责人用户名。
     */
    private String principalUsername;
    /**
     * 负责人昵称。
     */
    private String principalNickname;
    /**
     * 负责人头像。
     */
    private byte[] principalAvatar;
    /**
     * 优先级。
     */
    private int priority;
    /**
     * 需求来源。
     */
    private int sourceOfDemand;
    /**
     * 任务描述。
     */
    private String taskDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    /**
     * 任务开始时间。
     */
    private LocalDateTime startTime;
    /**
     * 任务截止时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;
    /**
     * 任务完成时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;
}
