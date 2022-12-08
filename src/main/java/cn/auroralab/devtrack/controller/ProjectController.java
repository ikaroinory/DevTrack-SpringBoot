package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.dto.ProjectDTO;
import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.form.UpdateProjectInformationForm;
import cn.auroralab.devtrack.po.Project;
import cn.auroralab.devtrack.service.MemberService;
import cn.auroralab.devtrack.service.ProjectService;
import cn.auroralab.devtrack.service.RoleService;
import cn.auroralab.devtrack.util.BitstreamGenerator;
import cn.auroralab.devtrack.util.JwtUtils;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final RoleService roleService;
    private final MemberService memberService;

    public ProjectController(ProjectService projectService, RoleService roleService, MemberService memberService) {
        this.projectService = projectService;
        this.roleService = roleService;
        this.memberService = memberService;
    }

    @PostMapping("/new")
    public ResponseVO<String> create(String name, String creatorUUID, String principalUUID, boolean publicProject, String description, String adminName, String memberName) {
        String projectUUID = BitstreamGenerator.parseUUID();

        String adminUUID;
        String memberUUID;
        try {
            adminUUID = roleService.newAdmin(projectUUID, adminName);
            memberUUID = roleService.newMember(projectUUID, memberName);
        } catch (ResponseException e) {
            return new ResponseVO<>(e.statusCode, null);
        }

        Project project = new Project();
        project.setUuid(projectUUID);
        project.setName(name);
        project.setCreator(creatorUUID);
        project.setPrincipal(principalUUID);
        project.setPublicProject(publicProject);
        project.setDescription(description);
        project.setDefaultRole(memberUUID);
        project.setDeleted(false);
        project.setCreationTime(LocalDateTime.now());

        try {
            projectService.create(project);
        } catch (ResponseException e) {
            return new ResponseVO<>(e.statusCode, null);
        }

        memberService.newRecord(principalUUID, projectUUID, adminUUID);

        return new ResponseVO<>(StatusCode.SUCCESS, projectUUID);
    }

    @PostMapping("/update")
    public StatusCode update(@RequestHeader(value = "Authorization") String authorization, UpdateProjectInformationForm form) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            projectService.update(requesterUUID, form);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @GetMapping("/get")
    @SkipTokenVerification
    public ResponseVO<ProjectDTO> get(String projectUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;
        ProjectDTO projectDTO = null;

        try {
            projectDTO = projectService.get(projectUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, projectDTO);
    }

    @GetMapping("/getOnePage")
    public ResponseVO<PageInformation<ProjectDTO>> getUserProjectList(@RequestHeader(value = "Authorization") String authorization, int pageNum, int pageSize) {
        String userUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;
        PageInformation<ProjectDTO> pageInformation = null;

        try {
            pageInformation = projectService.getUserProjectList(userUUID, pageNum, pageSize);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, pageInformation);
    }
}
