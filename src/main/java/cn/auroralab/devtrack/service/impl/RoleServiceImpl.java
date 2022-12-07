package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.MemberDAO;
import cn.auroralab.devtrack.dao.RoleDAO;
import cn.auroralab.devtrack.exception.role.RoleNotFoundException;
import cn.auroralab.devtrack.exception.role.RoleRecordExistException;
import cn.auroralab.devtrack.exception.system.InvalidParametersException;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.po.Role;
import cn.auroralab.devtrack.service.RoleService;
import cn.auroralab.devtrack.util.BitstreamGenerator;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.util.PaginationUtils;
import cn.auroralab.devtrack.util.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;
    private final MemberDAO memberDAO;

    public RoleServiceImpl(RoleDAO roleDAO, MemberDAO memberDAO) {
        this.roleDAO = roleDAO;
        this.memberDAO = memberDAO;
    }

    public void newRole(String requesterUUID, Role role)
            throws RequiredParametersIsEmptyException, PermissionDeniedException {
        boolean projectIsNull = role.getProject() == null || role.getProject().equals("");
        boolean nameIsNull = role.getName() == null || role.getName().equals("");

        if (projectIsNull || nameIsNull)
            throw new RequiredParametersIsEmptyException();

        Role roleOfUser = roleDAO.getRoleByUserInProject(requesterUUID, role.getProject());

        if (roleOfUser == null || !roleOfUser.getCreateRole())
            throw new PermissionDeniedException();


        role.setUuid(BitstreamGenerator.parseUUID());

        roleDAO.insert(role);
    }

    public String newAdmin(String projectUUID, String roleName)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(projectUUID, roleName);

        String recordUUID = BitstreamGenerator.parseUUID();

        Role role = new Role(
                recordUUID,
                projectUUID,
                roleName,
                true,
                true, true, true,
                true, true, true
        );
        roleDAO.insert(role);

        return recordUUID;
    }

    public String newMember(String projectUUID, String roleName)
            throws RequiredParametersIsEmptyException {
        String recordUUID = BitstreamGenerator.parseUUID();

        Role role = new Role(
                recordUUID,
                projectUUID,
                roleName,
                false,
                true, true, true,
                false, false, false
        );
        roleDAO.insert(role);

        return recordUUID;
    }

    public void updateRole(String requesterUUID, Role role)
            throws PermissionDeniedException {
        Role roleOfUser = roleDAO.getRoleByUserInProject(requesterUUID, role.getProject());

        if (roleOfUser == null || !roleOfUser.getUpdateRole())
            throw new PermissionDeniedException();

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Role.UUID, role.getUuid());

        Role newRole = new Role();
        newRole.setProject(null);
        newRole.setInviteMembers(role.getInviteMembers());
        newRole.setCreateTask(role.getCreateTask());
        newRole.setUpdateTask(role.getUpdateTask());
        newRole.setDeleteTask(role.getDeleteTask());

        roleDAO.update(newRole, queryWrapper);
    }

    public void removeRole(String requesterUUID, String roleUUID)
            throws RequiredParametersIsEmptyException, PermissionDeniedException, RoleRecordExistException, RoleNotFoundException {
        if (roleUUID == null || roleUUID.equals(""))
            throw new RequiredParametersIsEmptyException();

        Role roleOfUser = roleDAO.getRoleByUserInProject(requesterUUID, roleDAO.getProjectUUIDOfRole(roleUUID));

        if (roleOfUser == null || !roleOfUser.getRemoveRole())
            throw new PermissionDeniedException();
        if (memberDAO.countMemberWithRole(roleUUID) != 0)
            throw new RoleRecordExistException();

        roleDAO.deleteById(roleUUID);
    }

    public List<Role> getFromProject(String projectUUID)
            throws RequiredParametersIsEmptyException {
        Validator.notEmpty(projectUUID);

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Role.PROJECT, projectUUID);

        return roleDAO.selectList(queryWrapper);
    }

    public PageInformation<Role> getFromProject(String projectUUID, int pageNum, int pageSize)
            throws RequiredParametersIsEmptyException, InvalidParametersException {
        Validator.notEmpty(projectUUID);
        Validator.notEqual(0, pageNum, pageSize);

        PageInfo<Role> pageInfo;
        try (Page<Object> ignored = PageHelper.startPage(pageNum, pageSize)) {
            List<Role> list = getFromProject(projectUUID);
            pageInfo = new PageInfo<>(list);
        }

        return PaginationUtils.parsePageInformation(pageInfo);
    }
}
