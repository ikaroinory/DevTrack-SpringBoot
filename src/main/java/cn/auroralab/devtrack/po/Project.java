package cn.auroralab.devtrack.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@TableName(value = "projects", autoResultMap = true)
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UUID = "project_uuid";
    public static final String NAME = "project_name";
    public static final String CREATOR = "creator_uuid";
    public static final String PRINCIPAL = "principal_uuid";
    public static final String PUBLIC_PROJECT = "public_project";
    public static final String DESCRIPTION = "project_description";
    public static final String DEFAULT_ROLE = "default_role";
    public static final String STATUS = "project_status";
    public static final String IS_DELETED = "is_deleted";
    public static final String CREATION_TIME = "creation_time";
    public static final String START_TIME = "start_time";
    public static final String FINISH_TIME = "finish_time";
    public static final String DELETE_TIME = "delete_time";

    /**
     * 项目UUID。
     */
    @TableId(value = UUID, type = IdType.INPUT)
    private String uuid;
    /**
     * 项目名。
     */
    @TableField(value = NAME)
    private String name;
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
     * 项目是否公开。
     */
    @TableField(value = PUBLIC_PROJECT)
    private Boolean publicProject;
    /**
     * 项目描述。
     */
    @TableField(value = DESCRIPTION)
    private String description;
    /**
     * 默认角色UUID。
     */
    @TableField(value = DEFAULT_ROLE)
    private String defaultRole;
    /**
     * 项目是否已删除。
     */
    @TableField(value = IS_DELETED)
    private Boolean deleted;
    /**
     * 项目创建时间。
     */
    @TableField(value = CREATION_TIME)
    private LocalDateTime creationTime;
    /**
     * 项目开始时间。
     */
    @TableField(value = START_TIME)
    private LocalDateTime startTime;
    /**
     * 项目完成时间。
     */
    @TableField(value = FINISH_TIME)
    private LocalDateTime finishTime;
    /**
     * 项目删除时间。
     */
    @TableField(value = DELETE_TIME)
    private LocalDateTime deleteTime;
}
