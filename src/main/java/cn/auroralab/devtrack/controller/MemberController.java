package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.dto.MemberDTO;
import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.service.MemberService;
import cn.auroralab.devtrack.service.NotificationService;
import cn.auroralab.devtrack.util.JwtUtils;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final NotificationService notificationService;

    public MemberController(MemberService memberService, NotificationService notificationService) {
        this.memberService = memberService;
        this.notificationService = notificationService;
    }

    @PostMapping("/invite")
    public StatusCode inviteMembers(@RequestHeader(value = "Authorization") String authorization, String projectUUID, @RequestParam List<String> usernameList) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            notificationService.newProjectInvitations(requesterUUID, projectUUID, usernameList);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/accept")
    public StatusCode acceptInvitation(@RequestHeader(value = "Authorization") String authorization, String notificationUUID, String projectUUID) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        List<String> list = new ArrayList<>();
        list.add(requesterUUID);

        try {
            memberService.newDefaultRecords(requesterUUID, projectUUID, list);
            notificationService.handled(notificationUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/ignore")
    public StatusCode ignoreInvitation(String notificationUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            notificationService.handled(notificationUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/update")
    public StatusCode updateMemberRole(@RequestHeader(value = "Authorization") String authorization, String recordUUID, String roleUUID) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            memberService.updateMemberRole(requesterUUID, recordUUID, roleUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/remove")
    public StatusCode removeMembers(@RequestHeader(value = "Authorization") String authorization, @RequestParam List<String> recordUUIDList) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            memberService.removeMembers(requesterUUID, recordUUIDList);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @GetMapping("/getOnePageFromProject")
    @SkipTokenVerification
    public ResponseVO<PageInformation<MemberDTO>> getMemberList(String projectUUID, int pageNum, int pageSize) {
        StatusCode statusCode = StatusCode.SUCCESS;
        PageInformation<MemberDTO> pageInformation = null;

        try {
            pageInformation = memberService.getMemberList(projectUUID, pageNum, pageSize);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, pageInformation);
    }

    @GetMapping("/getAllFromProject")
    public ResponseVO<List<MemberDTO>> getAllFromProject(String projectUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;
        List<MemberDTO> list = null;

        try {
            list = memberService.getMemberList(projectUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, list);
    }
}
