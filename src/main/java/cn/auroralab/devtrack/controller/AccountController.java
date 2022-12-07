package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.dto.UserInformation;
import cn.auroralab.devtrack.dto.UserSearchItem;
import cn.auroralab.devtrack.enumeration.Gender;
import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.service.AccountService;
import cn.auroralab.devtrack.util.JwtUtils;
import cn.auroralab.devtrack.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signIn")
    @SkipTokenVerification
    public ResponseVO<String> signIn(String username, String password) {
        StatusCode statusCode = StatusCode.SUCCESS;
        String jwt = null;

        try {
            jwt = accountService.signIn(username, password);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, jwt);
    }

    @PostMapping("/autoSignIn")
    public StatusCode autoSignIn(@RequestHeader(value = "Authorization") String authorization) {
        String userUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            accountService.autoSignIn(userUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/signUp")
    @SkipTokenVerification
    public StatusCode signUp(String username, String password, String email, String vCode) {
        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            accountService.signUp(username, password, email, vCode);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updateProfile")
    public StatusCode updateProfile(String username, String nickname, Gender gender, String phone, String location, String website, String introduction) {
        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            accountService.updateProfile(username, nickname, gender, phone, location, website, introduction);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }


    @GetMapping("/getAvatar")
    @SkipTokenVerification
    public ResponseVO<byte[]> getAvatar(String username) {
        StatusCode statusCode = StatusCode.SUCCESS;
        byte[] avatar = null;

        try {
            avatar = accountService.getUserInformation(username).getAvatar();
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, avatar);
    }

    @PostMapping("/updateAvatar")
    public StatusCode updateAvatar(String username, MultipartFile file) {
        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            accountService.updateAvatar(username, file);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @GetMapping("/getUserInfo")
    @SkipTokenVerification
    public ResponseVO<UserInformation> getUserInformation(String username) {
        StatusCode statusCode = StatusCode.SUCCESS;
        UserInformation userInformation = null;

        try {
            userInformation = accountService.getUserInformation(username);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, userInformation);
    }

    @GetMapping("/search")
    @SkipTokenVerification
    public ResponseVO<List<UserSearchItem>> searchUsers(String prefix) {
        StatusCode statusCode = StatusCode.SUCCESS;
        List<UserSearchItem> list = null;

        try {
            list = accountService.searchUsers(prefix);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, list);
    }
}
