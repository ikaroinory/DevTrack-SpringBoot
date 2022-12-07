package cn.auroralab.devtrack.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SecretDAO {
    /**
     * 获取Token签名密钥。
     *
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    @Select("select secret from secrets where secret_name = md5('token')")
    String getTokenSecret();
}