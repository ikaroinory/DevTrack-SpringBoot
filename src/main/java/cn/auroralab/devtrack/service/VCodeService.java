package cn.auroralab.devtrack.service;

import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.system.UnknownException;
import cn.auroralab.devtrack.po.VCodeRecord;

public interface VCodeService {
    /**
     * 生成注册验证码。
     *
     * @param email 邮箱。
     * @author Guanyu Hu
     * @since 2022-10-23
     */
    VCodeRecord signUp(String email)
            throws RequiredParametersIsEmptyException, UnknownException;

    /**
     * 生成找回密码验证码。
     *
     * @param email 邮箱。
     * @author Guanyu Hu
     * @since 2022-12-11
     */
    VCodeRecord retrievePassword(String email)
            throws RequiredParametersIsEmptyException, UnknownException;
}
