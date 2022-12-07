package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    public final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    private final MimeMessage mimeMessage;
    private final MimeMessageHelper mimeMessageHelper;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;

        mimeMessage = javaMailSender.createMimeMessage();
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setText(String context, boolean html) {
        try {
            mimeMessageHelper.setText(context, html);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void addImage(String cid, String classPath) {
        addImage(cid, new DefaultResourceLoader().getResource("classpath:" + classPath));
    }

    public void addImage(String cid, Resource resource) {
        try {
            mimeMessageHelper.addInline(cid, resource);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEmail(String receiveEmail, String subject) {
        sendEmail("AuroraLab", receiveEmail, subject);
    }

    public void sendEmail(String senderName, String receiveEmail, String subject) {
        try {
            mimeMessageHelper.setFrom(senderName + "<" + senderEmail + ">");
            mimeMessageHelper.setTo(receiveEmail);
            mimeMessageHelper.setSubject(subject);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
