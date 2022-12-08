package cn.auroralab.devtrack.dto;

import lombok.Data;

@Data
public class TaskStatisticsDTO {
    public String date;
    public int creation;
    public int completion;
}
