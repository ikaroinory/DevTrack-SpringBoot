package cn.auroralab.devtrack.po;

import cn.auroralab.devtrack.enumeration.NotificationType;
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
@TableName(value = "notifications", autoResultMap = true)
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UUID = "record_uuid";
    public static final String TYPE = "type";
    public static final String TIME = "send_time";
    public static final String RECIPIENT = "recipient";
    public static final String TITLE = "notification_title";
    public static final String CONTEXT = "notification_context";
    public static final String HANDLING_TIME = "handling_time";
    public static final String DELETE_TIME = "delete_time";
    public static final String PARAM_UUID = "extra_param";

    /**
     * 通知记录UUID。
     */
    @TableId(value = UUID, type = IdType.INPUT)
    private String uuid;
    /**
     * 通知类型。
     */
    @TableField(value = TYPE)
    private NotificationType type;
    /**
     * 发送时间。
     */
    @TableField(value = TIME)
    private LocalDateTime time;
    /**
     * 收件人
     */
    @TableField(value = RECIPIENT)
    private String recipient;
    /**
     * 通知标题。
     */
    @TableField(value = TITLE)
    private String title;
    /**
     * 通知内容。
     */
    @TableField(value = CONTEXT)
    private String context;
    /**
     * 处理时间。
     */
    @TableField(value = HANDLING_TIME)
    private LocalDateTime handlingTime;
    /**
     * 删除时间。
     */
    @TableField(value = DELETE_TIME)
    private LocalDateTime deleteTime;
    /**
     * 额外参数。
     */
    @TableField(value = PARAM_UUID)
    private String paramUUID;
}
