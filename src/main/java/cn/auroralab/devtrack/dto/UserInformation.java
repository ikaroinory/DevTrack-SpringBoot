package cn.auroralab.devtrack.dto;

import cn.auroralab.devtrack.enumeration.Gender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInformation {
    private final String uuid;
    private final String username;
    private final byte[] avatar;
    private final String nickname;
    private final Gender gender;
    private final String email;
    private final String phone;
    private final String location;
    private final String website;
    private final String introduction;
    private final LocalDateTime signUpTime;
    private final LocalDateTime lastSignInTime;
    private final int notStartProject;
    private final int inProgressProject;
    private final int finishedProject;
    private final int notStartTask;
    private final int inProgressTask;
    private final int finishedTask;
}
