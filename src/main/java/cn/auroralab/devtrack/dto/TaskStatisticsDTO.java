package cn.auroralab.devtrack.dto;

import lombok.Data;

@Data
public class TaskStatisticsDTO {
    public String date;
    public Integer creation;
    public Integer completion;
}
