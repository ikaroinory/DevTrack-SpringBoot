package cn.auroralab.devtrack.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMemberDAO {
    /**
     * 插入一系列用户-任务映射记录。
     *
     * @param userUUIDList 用户UUID列表。
     * @param taskUUID     任务UUID。
     * @author Guanyu Hu
     * @since 2022-11-22
     */
    void newRecords(List<String> userUUIDList, String taskUUID);
}
