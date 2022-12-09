package cn.auroralab.devtrack.dao;

import cn.auroralab.devtrack.dto.HeatMapData;
import cn.auroralab.devtrack.dto.TaskDTO;
import cn.auroralab.devtrack.dto.TaskOverviewDTO;
import cn.auroralab.devtrack.dto.TaskStatisticsDTO;
import cn.auroralab.devtrack.po.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface TaskDAO extends BaseMapper<Task> {
    /**
     * 获取项目任务信息。
     *
     * @param projectUUID 项目UUID。
     * @author Guanyu Hu
     * @since 2022-11-22
     */
    List<TaskDTO> getTaskList(String projectUUID);

    /**
     * 获取用户过去一年完成的任务数量表，以天为单位统计。
     *
     * @param userUUID 用户UUID。
     * @author Guanyu Hu
     * @since 2022-11-09
     */
    List<HeatMapData> getTaskCountFinishedInThePastYear(String userUUID);

    /**
     * 获取项目的每日任务统计。
     *
     * @param projectUUID 项目UUID。
     * @author Guanyu Hu
     * @since 2022-12-09
     */
    List<TaskStatisticsDTO> getTaskStatistics(String projectUUID);

    /**
     * 获取项目的任务概览统计。
     *
     * @param projectUUID 项目UUID。
     * @author Guanyu Hu
     * @since 2022-12-09
     */
    @Select("{call get_task_overview(#{projectUUID, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    TaskOverviewDTO getTaskOverview(String projectUUID);

    /**
     * 更新任务完成时间。
     *
     * @param taskUUID 任务UUID。
     * @author Guanyu Hu
     * @since 2022-12-09
     */
    @Update("update tasks set finish_time = null where task_uuid = #{taskUUID}")
    int setTaskUnfinished(String taskUUID);

    /**
     * 更新任务完成时间。
     *
     * @param taskUUID   任务UUID。
     * @param finishTime 任务完成时间。
     * @author Guanyu Hu
     * @since 2022-12-09
     */
    @Update("update tasks set finish_time = now() where task_uuid = #{taskUUID}")
    int setTaskFinished(String taskUUID);
}
