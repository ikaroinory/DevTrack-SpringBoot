package cn.auroralab.devtrack.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    /**
     * 记录UUID。
     */
    private String recordUUID;
    /**
     * 所属的项目UUID。
     */
    private String projectUUID;
    /**
     * 用户UUID。
     */
    private String userUUID;
    /**
     * 用户名。
     */
    private String username;
    /**
     * 昵称。
     */
    private String nickname;
    /**
     * 头像。
     */
    private byte[] avatar;
    /**
     * 项目中的昵称。
     */
    private String nicknameInProject;
    /**
     * 项目中的角色uuid。
     */
    private String roleUUID;
    /**
     * 项目中的角色。
     */
    private String roleName;
    /**
     * 加入项目的时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinTime;
}
