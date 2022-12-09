package cn.auroralab.devtrack.dto;

import lombok.Data;

@Data
public class TaskOverviewDTO {
    private final int notStart;
    private final int inProgress;
    private final int completed;
}
