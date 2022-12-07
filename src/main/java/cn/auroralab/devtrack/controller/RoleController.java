package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.po.Role;
import cn.auroralab.devtrack.service.RoleService;
import cn.auroralab.devtrack.util.JwtUtils;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/new")
    public StatusCode create(@RequestHeader(value = "Authorization") String authorization, Role role) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            roleService.newRole(requesterUUID, role);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/update")
    public StatusCode update(@RequestHeader(value = "Authorization") String authorization, Role role) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            roleService.updateRole(requesterUUID, role);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/remove")
    public StatusCode remove(@RequestHeader(value = "Authorization") String authorization, String roleUUID) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            roleService.removeRole(requesterUUID, roleUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @GetMapping("/getAllFromProject")
    public ResponseVO<List<Role>> getFromProject(String projectUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;
        List<Role> list = null;

        try {
            list = roleService.getFromProject(projectUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, list);
    }

    @GetMapping("/getOnePageFromProject")
    public ResponseVO<PageInformation<Role>> getFromProject(String projectUUID, int pageNum, int pageSize) {
        StatusCode statusCode = StatusCode.SUCCESS;
        PageInformation<Role> pageInformation = null;

        try {
            pageInformation = roleService.getFromProject(projectUUID, pageNum, pageSize);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, pageInformation);
    }
}
