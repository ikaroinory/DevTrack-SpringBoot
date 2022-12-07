package cn.auroralab.devtrack.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UpdateProjectInformationForm {
    private String projectUUID;
    private String name;
    private String principalUUID;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
}
