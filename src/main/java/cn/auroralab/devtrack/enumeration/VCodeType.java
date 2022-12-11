package cn.auroralab.devtrack.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 验证码业务类型。
 *
 * @author Guanyu Hu
 * @since 2022-12-04
 */
@AllArgsConstructor
public enum VCodeType {
    /**
     * 未知业务。
     */
    UNKNOWN(0),

    /**
     * 注册。
     */
    SIGN_UP(100),

    /**
     * 登录认证。
     */
    SIGN_IN(201),
    /**
     * 修改密码认证。
     */
    CHANGE_PASSWORD(202),
    /**
     * 修改邮箱认证。
     */
    CHANGE_EMAIL(203),
    /**
     * 实名认证。
     */
    REAL_NAME(204),
    /**
     * 找回密码。
     */
    RETRIEVE_PASSWORD(205),
    ;

    @EnumValue
    @JsonValue
    public final int code;
}
