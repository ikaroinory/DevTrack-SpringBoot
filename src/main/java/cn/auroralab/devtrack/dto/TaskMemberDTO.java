package cn.auroralab.devtrack.dto;

import lombok.Data;

@Data
public class TaskMemberDTO {
    /**
     * 记录UUID。
     */
    private String recordUUID;
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
}
