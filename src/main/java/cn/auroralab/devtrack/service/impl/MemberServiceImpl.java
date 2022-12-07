package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.MemberDAO;
import cn.auroralab.devtrack.dto.MemberDTO;
import cn.auroralab.devtrack.exception.system.RecordNotFoundException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.po.Member;
import cn.auroralab.devtrack.service.MemberService;
import cn.auroralab.devtrack.util.BitstreamGenerator;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.util.PaginationUtils;
import cn.auroralab.devtrack.util.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberDAO, Member> implements MemberService {
    private final MemberDAO memberDAO;

    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public int newDefaultRecords(List<String> usernameList, String projectUUID) {
        return memberDAO.newDefaultRecords(usernameList, projectUUID);
    }

    public void newRecord(String userUUID, String projectUUID, String roleUUID) {
        Member member = new Member();

        member.setUuid(BitstreamGenerator.parseUUID());
        member.setFromProject(projectUUID);
        member.setUser(userUUID);
        member.setRole(roleUUID);

        memberDAO.insert(member);
    }

    public void updateMemberRole(String recordUUID, String roleUUID)
            throws RequiredParametersIsEmptyException, RecordNotFoundException {
        Validator.notEmpty(recordUUID, roleUUID);

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Member.UUID, recordUUID);

        if (memberDAO.selectById(recordUUID) == null)
            throw new RecordNotFoundException();

        Member member = new Member();
        member.setUuid(recordUUID);
        member.setRole(roleUUID);

        memberDAO.updateById(member);
    }

    public List<MemberDTO> getMemberList(String projectUUID)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(projectUUID);

        return memberDAO.getMemberList(projectUUID);
    }

    public PageInformation<MemberDTO> getMemberList(String projectUUID, int pageNum, int pageSize)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(projectUUID);

        PageInfo<MemberDTO> pageInfo;
        try (Page<Object> ignored = PageHelper.startPage(pageNum, pageSize)) {
            List<MemberDTO> list = memberDAO.getMemberList(projectUUID);
            pageInfo = new PageInfo<>(list);
        }
        return PaginationUtils.parsePageInformation(pageInfo);
    }
}
