package cn.auroralab.devtrack.dao;

import cn.auroralab.devtrack.dto.UserInformation;
import cn.auroralab.devtrack.po.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface AccountDAO extends BaseMapper<Account> {
    /**
     * 更新用户最后登录时间。
     *
     * @param userUUID 用户UUID。
     * @return 数据库更新行数。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    @Update("update accounts set last_sign_in_time = now() where user_uuid = #{userUUID}")
    int updateLastSignInTime(String userUUID);

    /**
     * 获取用户信息。
     *
     * @param username 用户名。
     * @author Guanyu Hu
     * @since 2022-12-05
     */
    @Select("{call get_user_info(#{username, mode=IN, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    UserInformation getUserInformation(String username);
}
