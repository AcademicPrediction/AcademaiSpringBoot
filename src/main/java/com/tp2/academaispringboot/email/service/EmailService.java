package com.tp2.academaispringboot.email.service;


import com.tp2.academaispringboot.util.EmailHtml;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessageForgotPassword(
            String to, String subject, String text) throws MessagingException, IOException {

        String htmlTemplate = EmailHtml.readHtmlFile("templates/EmailTemplate.html");

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setFrom("academaitesis@outlook.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(replaceEmailPlaceholder(htmlTemplate, to), true);

        emailSender.send(mimeMessage);
    }

    public void sendHtmlEmail(String name, String email, String phone, String message, String subject) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom("academaitesis@outlook.com");
            helper.setTo("academaitesis@outlook.com");
            helper.setSubject(subject);
            helper.setText(EmailHtml.generateHtmlContent(name, email, phone, message), true);

            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }

    public String replaceEmailPlaceholder(String htmlContent, String email) {
        return htmlContent.replace("{{email}}", email);
    }

}
