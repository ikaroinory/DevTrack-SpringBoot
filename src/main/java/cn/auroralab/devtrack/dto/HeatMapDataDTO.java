package cn.auroralab.devtrack.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HeatMapDataDTO {
    private final LocalDate date;
    private final Integer count;
}
