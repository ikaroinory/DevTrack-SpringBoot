package cn.auroralab.devtrack.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("task_members")
public class TaskMember implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String RECORD_UUID = "record_uuid";
    public static final String USER_UUID = "user_uuid";
    public static final String TASK_UUID = "task_uuid";

    @TableId(value = RECORD_UUID, type = IdType.INPUT)
    private String recordUUID;
    @TableField(value = USER_UUID)
    private String userUUID;
    @TableField(value = TASK_UUID)
    private String taskUUID;
}