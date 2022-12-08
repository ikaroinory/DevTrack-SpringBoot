package cn.auroralab.devtrack.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UpdateTaskTimeForm {
    private final String taskUUID;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private final LocalDateTime time;
}
