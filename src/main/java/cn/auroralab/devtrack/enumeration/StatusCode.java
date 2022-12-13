package cn.auroralab.devtrack.enumeration;

import cn.auroralab.devtrack.util.Parseable;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 响应状态码。
 *
 * @author Guanyu Hu
 * @since 2022-10-17
 */
@AllArgsConstructor
public enum StatusCode implements Parseable {
    /*
     * 状态码设计规则
     *
     * 除未知状态外，状态码均使用包含三位数，其中，中间位为具体事务基于低位的扩充位，正常扩充时由小到大，特殊扩充时由大到小。其他两位见下表。
     *
     * | High |0        | 1      | 2         | 3      | 4     | 5        | 6     | 7       | 8     | 9            |
     * |------|---------|--------|-----------|--------|-------|----------|-------|---------|-------|--------------|
     * | Task | Unknown | Global | Token     | V-Code | Users | Projects | Roles | Members | Tasks | Notification |
     *
     * | Low  | 0       | 1      | 2         | 3      | 4     | 5        |
     * |------|---------|--------|-----------|--------|-------|----------|
     * | Task | Success | Error  | Not Found | Exists | Empty | Invalid  |
     */

    /**
     * 未知。
     */
    UNKNOWN(0),

    /**
     * 成功。
     */
    SUCCESS(100),
    /**
     * 记录不存在。
     */
    RECORD_NOT_FOUND(102),
    /**
     * 无效的参数。
     */
    INVALID_PARAMS(105),
    /**
     * 必填参数为空。
     */
    REQUIRED_PARAMS_IS_EMPTY(114),
    /**
     * 无效的文件类型。
     */
    INVALID_FILETYPE(115),
    /**
     * 数据未更新。
     */
    NOT_UPDATE(197),
    /**
     * 权限被拒绝。
     */
    PERMISSION_DENIED(198),
    /**
     * UUID冲突。
     */
    UUID_CONFLICT(199),


    /**
     * Token为空。
     */
    TOKEN_IS_EMPTY(201),
    /**
     * 过期的Token。
     */
    EXPIRED_TOKEN(202),
    /**
     * 无效的Token。
     */
    INVALID_TOKEN(203),

    /**
     * 重新发送的验证码。
     */
    RESEND_VCODE(300),
    /**
     * 验证码记录不存在。
     */
    VCODE_RECORD_NOT_FOUND(301),
    /**
     * 验证码过期。
     */
    EXPIRED_VCODE(302),
    /**
     * 验证码错误。
     */
    VCODE_ERROR(303),
    /**
     * 无效的验证码。
     */
    INVALID_VCODE(305),

    /**
     * 密码错误。
     */
    PASSWORD_ERROR(401),
    /**
     * 用户不存在。
     */
    USER_NOT_FOUND(402),
    /**
     * 用户已存在。
     */
    USER_EXISTED(403),

    /**
     * 项目不存在。
     */
    PROJECT_NOT_FOUND(502),

    /**
     * 角色不存在。
     */
    ROLE_NOT_FOUND(602),
    /**
     * 角色存在成员使用（通常在删除成员时抛出该异常）。
     */
    ROLE_RECORD_EXIST(603),

    /**
     * 任务不存在。
     */
    TASK_NOT_FOUND(802),

    /**
     * 通知不存在。
     */
    NOTIFICATION_NOT_FOUND(902),
    ;

    @EnumValue
    @JsonValue
    public final int code;

    public int getIdentificationCode() {
        return code;
    }
}
