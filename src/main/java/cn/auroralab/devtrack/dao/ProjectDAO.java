package cn.auroralab.devtrack.dao;

import cn.auroralab.devtrack.dto.ProjectDTO;
import cn.auroralab.devtrack.po.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface ProjectDAO extends BaseMapper<Project> {
    /**
     * 调用存储过程获取项目信息。
     *
     * @param projectUUID 项目UUID。
     * @author Guanyu Hu
     * @since 2022-12-05
     */
    @Select("{call get_project_info(#{projectUUID, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    ProjectDTO get(String projectUUID);

    /**
     * 获取用户的项目列表。
     *
     * @param userUUID 用户UUID。
     * @author Guanyu Hu
     * @since 2022-11-21
     */
    List<ProjectDTO> getList(String userUUID);
}
