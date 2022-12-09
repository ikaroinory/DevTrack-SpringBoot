package cn.auroralab.devtrack.dto;

import lombok.Data;

@Data
public final class PlannedCompletionDTO {
    private final int withinDeadlines;
    private final int withoutDeadlines;
}
