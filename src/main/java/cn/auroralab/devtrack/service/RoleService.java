package cn.auroralab.devtrack.service;

import cn.auroralab.devtrack.exception.role.RoleNotFoundException;
import cn.auroralab.devtrack.exception.role.RoleRecordExistException;
import cn.auroralab.devtrack.exception.system.InvalidParametersException;
import cn.auroralab.devtrack.exception.system.PermissionDeniedException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.po.Role;
import cn.auroralab.devtrack.util.PageInformation;

import java.util.List;

public interface RoleService {
    /**
     * 创建新角色。
     *
     * @param requesterUUID 请求用户UUID。
     * @param role          角色实体。
     * @author Xiaotong Wu, Guanyu Hu
     * @since 2022-11-20
     */
    void newRole(String requesterUUID, Role role)
            throws RequiredParametersIsEmptyException, PermissionDeniedException;

    /**
     * 创建管理员角色。
     *
     * @param projectUUID 项目UUID。
     * @param roleName    角色名称。
     * @return 管理员角色UUID。
     * @author Guanyu Hu
     * @since 2022-12-03
     */
    String newAdmin(String projectUUID, String roleName)
            throws RequiredParametersIsEmptyException;

    /**
     * 创建成员角色。
     *
     * @param projectUUID 项目UUID。
     * @param roleName    角色名称。
     * @return 成员角色UUID。
     * @author Guanyu Hu
     * @since 2022-12-03
     */
    String newMember(String projectUUID, String roleName)
            throws RequiredParametersIsEmptyException;

    /**
     * 修改角色。
     *
     * @param requesterUUID 请求用户UUID。
     * @param role          角色实体。
     * @author Guanyu Hu
     * @since 2022-12-02
     */
    void updateRole(String requesterUUID, Role role)
            throws PermissionDeniedException;

    /**
     * 移除角色。
     *
     * @param requesterUUID 请求用户UUID。
     * @param roleUUID      角色UUID。
     * @author Guanyu Hu
     * @since 2022-12-02
     */
    void removeRole(String requesterUUID, String roleUUID)
            throws RequiredParametersIsEmptyException, PermissionDeniedException, RoleRecordExistException, RoleNotFoundException;

    /**
     * 获取项目中的所有角色。
     *
     * @param projectUUID 项目UUID。
     * @author Guanyu Hu
     * @since 2022-12-01
     */
    List<Role> getFromProject(String projectUUID)
            throws RequiredParametersIsEmptyException;

    /**
     * 获取一页项目中的角色。
     *
     * @param projectUUID 项目UUID。
     * @param pageNum     页码。
     * @param pageSize    每页大小。
     * @author Guanyu Hu
     * @since 2022-12-05
     */
    PageInformation<Role> getFromProject(String projectUUID, int pageNum, int pageSize)
            throws RequiredParametersIsEmptyException, InvalidParametersException;
}
