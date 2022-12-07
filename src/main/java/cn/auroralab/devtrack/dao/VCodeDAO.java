package cn.auroralab.devtrack.dao;

import cn.auroralab.devtrack.enumeration.VCodeType;
import cn.auroralab.devtrack.po.VCodeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VCodeDAO extends BaseMapper<VCodeRecord> {
    /**
     * 获取指定验证码类型的某邮箱的最后一条验证码记录。
     *
     * @param type  验证码类型。
     * @param email 邮箱。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    VCodeRecord getLatestRecord(VCodeType type, String email);
}
