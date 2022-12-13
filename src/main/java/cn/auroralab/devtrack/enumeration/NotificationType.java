package cn.auroralab.devtrack.enumeration;

import cn.auroralab.devtrack.util.Parseable;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 通知类型。
 *
 * @author Guanyu Hu
 * @since 2022-12-11
 */
@AllArgsConstructor
public enum NotificationType implements Parseable {
    /**
     * 未知
     */
    UNKNOWN(0),

    /**
     * 系统通知。
     */
    SYSTEM(100),

    /**
     * 项目邀请。
     */
    PROJECT_INVITATION(200),
    ;

    @EnumValue
    @JsonValue
    public final int code;

    public int getIdentificationCode() {
        return code;
    }
}
