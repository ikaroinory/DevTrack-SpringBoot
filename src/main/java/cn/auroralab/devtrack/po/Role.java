package cn.auroralab.devtrack.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "project_roles", autoResultMap = true)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UUID = "role_uuid";
    public static final String PROJECT = "from_project";
    public static final String NAME = "role_name";
    public static final String INVITE_MEMBER = "invite_member";
    public static final String UPDATE_MEMBER = "update_member";
    public static final String REMOVE_MEMBER = "remove_member";
    public static final String UPDATE_PROJECT = "update_project";
    public static final String DELETE_PROJECT = "delete_project";
    public static final String CREATE_TASK = "create_task";
    public static final String UPDATE_TASK = "update_task";
    public static final String DELETE_TASK = "delete_task";
    public static final String CREATE_ROLE = "create_role";
    public static final String UPDATE_ROLE = "update_role";
    public static final String REMOVE_ROLE = "remove_role";

    /**
     * 角色UUID。
     */
    @TableId(value = UUID, type = IdType.INPUT)
    private String uuid;
    /**
     * 角色所属项目的UUID。
     */
    @TableField(value = PROJECT)
    private String project;
    /**
     * 角色名。
     */
    @TableField(value = NAME)
    private String name;
    /**
     * 邀请成员权限。
     */
    @TableField(value = INVITE_MEMBER)
    private Boolean inviteMember;
    /**
     * 更新成员信息权限。
     */
    @TableField(value = UPDATE_MEMBER)
    private Boolean updateMember;
    /**
     * 移除成员权限。
     */
    @TableField(value = REMOVE_MEMBER)
    private Boolean removeMember;
    /**
     * 更新项目权限。
     */
    @TableField(value = UPDATE_PROJECT)
    private Boolean updateProject;
    /**
     * 删除项目权限。
     */
    @TableField(value = DELETE_PROJECT)
    private Boolean deleteProject;
    /**
     * 创建任务权限。
     */
    @TableField(value = CREATE_TASK)
    private Boolean createTask;
    /**
     * 修改任务权限。
     */
    @TableField(value = UPDATE_TASK)
    private Boolean updateTask;
    /**
     * 删除任务权限。
     */
    @TableField(value = DELETE_TASK)
    private Boolean deleteTask;
    /**
     * 创建角色权限。
     */
    @TableField(value = CREATE_ROLE)
    private Boolean createRole;
    /**
     * 修改角色权限。
     */
    @TableField(value = UPDATE_ROLE)
    private Boolean updateRole;
    /**
     * 删除角色权限。
     */
    @TableField(value = REMOVE_ROLE)
    private Boolean removeRole;

    public Role(String recordUUID, String projectUUID, String roleName) {
        this();
        uuid = recordUUID;
        project = projectUUID;
        name = roleName;
    }
}
