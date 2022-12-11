package cn.auroralab.devtrack.po;

import cn.auroralab.devtrack.enumeration.Priority;
import cn.auroralab.devtrack.enumeration.SourceOfDemand;
import cn.auroralab.devtrack.enumeration.TaskType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tasks", autoResultMap = true)
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UUID = "task_uuid";
    public static final String FROM_PROJECT = "from_project";
    public static final String TYPE = "task_type";
    public static final String TITLE = "task_title";
    public static final String CREATOR = "creator_uuid";
    public static final String PRINCIPAL = "principal_uuid";
    public static final String PRIORITY = "priority";
    public static final String SOURCE_OF_DEMAND = "source_of_demand";
    public static final String DESCRIPTION = "task_description";
    public static final String DELETED = "is_deleted";
    public static final String CREATION_TIME = "creation_time";
    public static final String START_TIME = "start_time";
    public static final String DEADLINE = "deadline";
    public static final String FINISH_TIME = "finish_time";
    public static final String DELETE_TIME = "delete_time";

    /**
     * 任务UUID。
     */
    @TableId(value = UUID, type = IdType.INPUT)
    private String uuid;
    /**
     * 任务所属的项目UUID。
     */
    @TableField(value = FROM_PROJECT)
    private String fromProject;
    /**
     * 任务类型。
     */
    @TableField(value = TYPE)
    private TaskType type;
    /**
     * 任务标题。
     */
    @TableField(value = TITLE)
    private String title;
    /**
     * 创建人UUID。
     */
    @TableField(value = CREATOR)
    private String creator;
    /**
     * 负责人UUID。
     */
    @TableField(value = PRINCIPAL)
    private String principal;
    /**
     * 优先级。
     */
    @TableField(value = PRIORITY)
    private Priority priority;
    /**
     * 需求来源。
     */
    @TableField(value = SOURCE_OF_DEMAND)
    private SourceOfDemand sourceOfDemand;
    /**
     * 任务描述。
     */
    @TableField(value = DESCRIPTION)
    private String description;
    /**
     * 是否已删除。
     */
    @TableField(value = DELETED)
    private Boolean deleted;
    /**
     * 任务创建时间。
     */
    @TableField(value = CREATION_TIME)
    private LocalDateTime creationTime;
    /**
     * 任务开始时间。
     */
    @TableField(value = START_TIME)
    private LocalDateTime startTime;
    /**
     * 任务截止时间。
     */
    @TableField(value = DEADLINE)
    private LocalDateTime deadline;
    /**
     * 任务完成时间。
     */
    @TableField(value = FINISH_TIME)
    private LocalDateTime finishTime;
    /**
     * 任务删除时间。
     */
    @TableField(value = DELETE_TIME)
    private LocalDateTime deleteTime;
}
