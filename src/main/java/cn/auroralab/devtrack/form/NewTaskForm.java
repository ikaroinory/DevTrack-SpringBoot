package cn.auroralab.devtrack.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NewTaskForm {
    /**
     * 所属项目的UUID。
     */
    private final String fromProject;
    /**
     * 任务类型。
     */
    private final int type;
    /**
     * 任务标题。
     */
    private final String title;
    /**
     * 负责人的uuid。
     */
    private final String principal;
    /**
     * 优先级。
     */
    private final int priority;
    /**
     * 需求来源。
     */
    private final int sourceOfDemand;
    /**
     * 任务描述。
     */
    private final String description;
    /**
     * 任务开始时间。
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private final LocalDateTime startTime;
    /**
     * 任务截止时间。
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private final LocalDateTime deadline;
    /**
     * 参与人员的UUID列表。
     */
    private final List<String> members;
}
