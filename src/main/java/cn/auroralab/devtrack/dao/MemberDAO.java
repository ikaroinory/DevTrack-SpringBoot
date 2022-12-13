package cn.auroralab.devtrack.dao;

import cn.auroralab.devtrack.dto.MemberDTO;
import cn.auroralab.devtrack.po.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO extends BaseMapper<Member> {
    /**
     * 插入一系列默认角色的用户-项目映射。
     *
     * @param userUUIDList 用户UUID列表。
     * @param projectUUID  项目uuid。
     * @return 成功添加的行数。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    int newDefaultRecords(List<String> userUUIDList, String projectUUID);

    /**
     * 获取项目成员信息。
     *
     * @param projectUUID 项目UUID。
     * @return 成员信息列表。
     * @author Guanyu Hu
     * @since 2022-11-18
     */
    List<MemberDTO> getMemberList(String projectUUID);

    /**
     * 统计某角色的成员数。
     *
     * @param roleUUID 角色uuid。
     * @return 计数结果。
     * @author Guanyu Hu
     * @since 2022-12-02
     */
    long countMemberWithRole(String roleUUID);
}
