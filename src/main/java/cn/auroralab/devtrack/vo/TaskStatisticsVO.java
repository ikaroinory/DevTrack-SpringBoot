package cn.auroralab.devtrack.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskStatisticsVO {
    private final List<String> dateList;
    private final List<Integer> creationList;
    private final List<Integer> completionList;

    public TaskStatisticsVO() {
        dateList = new ArrayList<>();
        creationList = new ArrayList<>();
        completionList = new ArrayList<>();
    }
}
