package cn.auroralab.devtrack.po;

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
@TableName("project_members")
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UUID = "record_uuid";
    public static final String PROJECT_UUID = "from_project";
    public static final String USER_UUID = "user_uuid";
    public static final String ROLE = "role";
    public static final String TIME = "record_time";

    /**
     * 映射记录UUID。
     */
    @TableId(value = UUID, type = IdType.INPUT)
    private String uuid;
    /**
     * 所属项目。
     */
    @TableField(value = PROJECT_UUID)
    private String fromProject;
    /**
     * 用户UUID。
     */
    @TableField(value = USER_UUID)
    private String user;
    /**
     * 用户在项目中扮演的角色。
     */
    @TableField(value = ROLE)
    private String role;
    /**
     * 用户加入该项目的时间。
     */
    @TableField(value = TIME)
    private LocalDateTime time;
}
