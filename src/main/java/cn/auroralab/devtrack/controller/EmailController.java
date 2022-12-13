package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.enumeration.VCodeType;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.po.VCodeRecord;
import cn.auroralab.devtrack.service.EmailService;
import cn.auroralab.devtrack.service.VCodeService;
import cn.auroralab.devtrack.util.ResourceFileLoader;
import cn.auroralab.devtrack.vo.ResponseVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/mail")
public class EmailController {
    private final EmailService emailService;
    private final VCodeService vCodeService;

    public EmailController(EmailService emailService, VCodeService vCodeService) {
        this.emailService = emailService;
        this.vCodeService = vCodeService;
    }

    @PostMapping("/signUpVCode")
    @SkipTokenVerification
    public ResponseVO<String> sendSignUpVCodeEmail(String email) {
        VCodeRecord record;
        try {
            record = vCodeService.newRecord(email, VCodeType.SIGN_UP);
        } catch (ResponseException e) {
            return new ResponseVO<>(e.statusCode, null);
        }

        LocalDateTime invalidTime = record.getTime().plusMinutes(record.getValidTime());
        String invalidTimeString = invalidTime.toString().split("\\.")[0].replace("T", " ");

        String subject = "AuroraLab Verification Code";
        String text = ResourceFileLoader.readFile("EmailTemplates/SignUp.html")
                .replace("{{$vcode}}", record.getVCode())
                .replace("{{$validTime}}", String.valueOf(record.getValidTime()))
                .replace("{{$time}}", invalidTimeString)
                .replace("{{$email}}", email);

        emailService.setText(text, true);
        emailService.addImage("logo", "img/logo_long.png");
        emailService.sendEmail(email, subject);

        return new ResponseVO<>(StatusCode.SUCCESS, record.getUuid());
    }

    @PostMapping("/forgetVCode")
    @SkipTokenVerification
    public ResponseVO<String> sendForgetPasswordVCodeEmail(String email) {
        VCodeRecord record;
        try {
            record = vCodeService.newRecord(email, VCodeType.RETRIEVE_PASSWORD);
        } catch (ResponseException e) {
            return new ResponseVO<>(e.statusCode, null);
        }

        LocalDateTime invalidTime = record.getTime().plusMinutes(record.getValidTime());
        String invalidTimeString = invalidTime.toString().split("\\.")[0].replace("T", " ");

        String subject = "AuroraLab Verification Code";
        String text = ResourceFileLoader.readFile("EmailTemplates/RetrievePassword.html")
                .replace("{{$vcode}}", record.getVCode())
                .replace("{{$validTime}}", String.valueOf(record.getValidTime()))
                .replace("{{$time}}", invalidTimeString)
                .replace("{{$email}}", email);

        emailService.setText(text, true);
        emailService.addImage("logo", "img/logo_long.png");
        emailService.sendEmail(email, subject);

        return new ResponseVO<>(StatusCode.SUCCESS, record.getUuid());
    }
}
