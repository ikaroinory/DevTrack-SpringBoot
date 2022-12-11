package cn.auroralab.devtrack.service;

import cn.auroralab.devtrack.enumeration.VCodeType;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.system.UnknownException;
import cn.auroralab.devtrack.po.VCodeRecord;

public interface VCodeService {
    /**
     * 生成一条新验证码记录。
     *
     * @param email 邮箱。
     * @param type  验证码类型。
     * @author Guanyu Hu
     * @since 2022-12-11
     */
    VCodeRecord newRecord(String email, VCodeType type)
            throws RequiredParametersIsEmptyException, UnknownException;
}
