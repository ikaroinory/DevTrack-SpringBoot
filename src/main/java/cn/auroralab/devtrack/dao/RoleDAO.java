package cn.auroralab.devtrack.dao;

import cn.auroralab.devtrack.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDAO extends BaseMapper<Role> {
    /**
     * 获取用户在某项目中的角色。
     *
     * @param userUUID    用户uuid。
     * @param projectUUID 项目uuid。
     * @return 角色实体。
     * @author Xiaotong Wu
     * @since 2022-11-21
     */
    Role getRoleByUserInProject(String userUUID, String projectUUID);

    /**
     * 获取角色所在的项目uuid。
     *
     * @param roleUUID 角色uuid。
     * @return 项目uuid。
     * @author Guanyu Hu
     * @since 2022-12-03
     */
    String getProjectUUIDOfRole(String roleUUID);
}
