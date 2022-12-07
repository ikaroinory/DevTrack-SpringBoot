package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.dto.MemberDTO;
import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.service.MemberService;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/add")
    public ResponseVO<Integer> addMembers(String projectUUID, @RequestParam List<String> usernameList) {
        return new ResponseVO<>(StatusCode.SUCCESS, memberService.newDefaultRecords(usernameList, projectUUID));
    }

    @PostMapping("/update")
    public StatusCode updateMemberRole(String recordUUID, String roleUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            memberService.updateMemberRole(recordUUID, roleUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/remove")
    public StatusCode removeMembers(@RequestParam List<String> recordUUIDList) {
        return memberService.removeByIds(recordUUIDList) ? StatusCode.SUCCESS : StatusCode.UNKNOWN;
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
