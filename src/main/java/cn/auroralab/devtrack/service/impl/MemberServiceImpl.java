package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.MemberDAO;
import cn.auroralab.devtrack.dao.RoleDAO;
import cn.auroralab.devtrack.dto.MemberDTO;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RecordNotFoundException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.po.Member;
import cn.auroralab.devtrack.po.Role;
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
    private final RoleDAO roleDAO;

    public MemberServiceImpl(MemberDAO memberDAO, RoleDAO roleDAO) {
        this.memberDAO = memberDAO;
        this.roleDAO = roleDAO;
    }

    public int newDefaultRecords(String requesterUUID, String projectUUID, List<String> usernameList)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(projectUUID);

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

    public void updateMemberRole(String requesterUUID, String recordUUID, String roleUUID)
            throws RequiredParametersIsEmptyException, RecordNotFoundException, PermissionDeniedException {
        Validator.notEmpty(recordUUID, roleUUID);

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Member.UUID, recordUUID);

        Member member = memberDAO.selectById(recordUUID);
        if (member == null)
            throw new RecordNotFoundException();

        Role role = roleDAO.getRoleByUserInProject(requesterUUID, member.getFromProject());
        if (role == null || !role.getUpdateMember())
            throw new PermissionDeniedException();

        Member newMember = new Member();
        newMember.setUuid(recordUUID);
        newMember.setRole(roleUUID);

        memberDAO.updateById(newMember);
    }

    public void removeMembers(String requestUUID, List<String> recordUUIDList)
            throws RequiredParametersIsEmptyException, PermissionDeniedException {
        Validator.notEmpty(requestUUID);
        Validator.notEmpty(recordUUIDList);

        String projectUUID = memberDAO.selectById(recordUUIDList.get(0)).getFromProject();

        Role role = roleDAO.getRoleByUserInProject(requestUUID, projectUUID);

        if (role == null || !role.getRemoveMember())
            throw new PermissionDeniedException();

        removeByIds(recordUUIDList);
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
