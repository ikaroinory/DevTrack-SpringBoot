package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.VCodeDAO;
import cn.auroralab.devtrack.enumeration.VCodeType;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.system.UnknownException;
import cn.auroralab.devtrack.po.VCodeRecord;
import cn.auroralab.devtrack.service.VCodeService;
import cn.auroralab.devtrack.util.BitstreamGenerator;
import cn.auroralab.devtrack.util.VCodeGenerator;
import cn.auroralab.devtrack.util.Validator;
import org.springframework.stereotype.Service;

@Service
public class VCodeServiceImpl implements VCodeService {
    private final VCodeDAO vCodeDAO;

    public VCodeServiceImpl(VCodeDAO vCodeDAO) { this.vCodeDAO = vCodeDAO; }

    public VCodeRecord signUp(String email)
            throws RequiredParametersIsEmptyException, UnknownException {
        Validator.notEmpty(email);

        VCodeRecord latestRecord = vCodeDAO.getLatestRecord(VCodeType.SIGN_UP, email);

        if (latestRecord != null)
            return latestRecord;

        VCodeGenerator generator = new VCodeGenerator();
        VCodeRecord newRecord = new VCodeRecord();

        newRecord.setUuid(BitstreamGenerator.parseUUID());
        newRecord.setTime(generator.getStartTime());
        newRecord.setType(VCodeType.SIGN_UP);
        newRecord.setEmail(email);
        newRecord.setVCode(generator.getVCode());
        newRecord.setValidTime(generator.getValidTime());

        int insert = vCodeDAO.insert(newRecord);

        if (insert == 0)
            throw new UnknownException();

        return newRecord;
    }
}
