package cn.auroralab.devtrack.po;

import cn.auroralab.devtrack.enumeration.VCodeType;
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
@TableName(value = "vcode_records", autoResultMap = true)
public class VCodeRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UUID = "task_uuid";
    public static final String TIME = "task_time";
    public static final String TYPE = "task_type";
    public static final String EMAIL = "email";
    public static final String VCODE = "vcode";
    public static final String VALID_TIME = "valid_time";

    /**
     * 验证码业务uuid。
     */
    @TableId(value = UUID, type = IdType.INPUT)
    private String uuid;
    /**
     * 业务时间。
     */
    @TableField(value = TIME)
    private LocalDateTime time;
    /**
     * 验证码业务类型。
     */
    @TableField(value = TYPE)
    private VCodeType type;
    /**
     * 接收验证码的邮箱。
     */
    @TableField(value = EMAIL)
    private String email;
    /**
     * 验证码
     */
    @TableField(value = VCODE)
    private String vCode;
    /**
     * 验证码有效时间，单位：分钟
     */
    @TableField(value = VALID_TIME)
    private int validTime;
}
