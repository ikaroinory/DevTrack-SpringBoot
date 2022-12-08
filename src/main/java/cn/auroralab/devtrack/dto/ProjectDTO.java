package cn.auroralab.devtrack.dto;

import cn.auroralab.devtrack.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 项目数据传输对象（可代替值对象）。
 *
 * @author Guanyu Hu
 * @since 2022-12-05
 */
@Data
public class ProjectDTO {
    /**
     * 项目UUID。
     */
    private String uuid;
    /**
     * 项目名。
     */
    private String name;
    /**
     * 创建人UUID。
     */
    private String creatorUUID;
    /**
     * 创建人用户名。
     */
    private String creatorUsername;
    /**
     * 创建人昵称。
     */
    private String creatorNickname;
    /**
     * 创建人头像。
     */
    private byte[] creatorAvatar;
    /**
     * 负责人UUID。
     */
    private String principalUUID;
    /**
     * 负责人用户名。
     */
    private String principalUsername;
    /**
     * 负责人昵称。
     */
    private String principalNickname;
    /**
     * 负责人头像。
     */
    private byte[] principalAvatar;
    /**
     * 项目描述。
     */
    private String description;
    /**
     * 创建时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    /**
     * 项目开始时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 项目完成时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;
    /**
     * 项目状态。
     */
    private Status status;
    /**
     * 项目进度。百分制。
     */
    private Double progress;
}
