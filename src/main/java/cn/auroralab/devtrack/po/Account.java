package cn.auroralab.devtrack.po;

import cn.auroralab.devtrack.enumeration.Gender;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户账号持久对象。
 *
 * @author Guanyu Hu
 * @since 2022-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@TableName(value = "accounts", autoResultMap = true)
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UUID = "user_uuid";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password_digest";
    public static final String AVATAR = "avatar";
    public static final String NICKNAME = "nickname";
    public static final String GENDER = "gender";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String LOCATION = "location";
    public static final String WEBSITE = "website";
    public static final String INTRODUCTION = "introduction";
    public static final String SIGN_UP_TIME = "sign_up_time";
    public static final String CANCELLATION_TIME = "cancellation_time";
    public static final String LAST_SIGN_IN_TIME = "last_sign_in_time";

    /**
     * 用户uuid
     */
    @TableId(value = UUID, type = IdType.INPUT)
    private String uuid;
    /**
     * 用户名
     */
    @TableField(value = USERNAME)
    private String username;
    /**
     * 密码的MD5值
     */
    @TableField(value = PASSWORD)
    private String passwordDigest;
    /**
     * 头像，使用二进制保存
     */
    @TableField(value = AVATAR)
    private byte[] avatar;
    /**
     * 昵称
     */
    @TableField(value = NICKNAME)
    private String nickname;
    /**
     * 性别
     */
    @TableField(value = GENDER)
    private Gender gender;
    /**
     * 邮箱
     */
    @TableField(value = EMAIL)
    private String email;
    /**
     * 手机号
     */
    @TableField(value = PHONE)
    private String phone;
    /**
     * 位置
     */
    @TableField(value = LOCATION)
    private String location;
    /**
     * 个人主页
     */
    @TableField(value = WEBSITE)
    private String website;
    /**
     * 个人介绍
     */
    @TableField(value = INTRODUCTION)
    private String introduction;
    /**
     * 注册时间
     */
    @TableField(value = SIGN_UP_TIME)
    private LocalDateTime signUpTime;
    /**
     * 注销时间
     */
    @TableField(value = CANCELLATION_TIME)
    private LocalDateTime cancellationTime;
    /**
     * 上次登录时间
     */
    @TableField(value = LAST_SIGN_IN_TIME)
    private LocalDateTime lastSignInTime;
}
