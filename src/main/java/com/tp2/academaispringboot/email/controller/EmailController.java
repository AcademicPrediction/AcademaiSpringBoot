package com.tp2.academaispringboot.email.controller;

import com.tp2.academaispringboot.email.dto.EmailResource;
import com.tp2.academaispringboot.email.dto.EmailResponseResource;
import com.tp2.academaispringboot.email.dto.MessageType;
import com.tp2.academaispringboot.email.service.EmailService;
import com.tp2.academaispringboot.supervisor.service.SupervisorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Email", description = "Email API")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SupervisorService supervisorService;

    @Operation(summary = "Send email", description = "Send email")
    @PostMapping(value = "/send-email",consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmailResponseResource> sendEmail(@RequestBody EmailResource emailResource) throws MessagingException, IOException {

        String requestHelp = "";
        String email = emailResource.getEmail();

        EmailResponseResource emailNotFoundResource = new EmailResponseResource();

        if (emailResource.getMessageTypeEnum() == MessageType.FORGOT_PASSWORD) {

            if (supervisorService.getSupervisorByEmail(emailResource.getEmail()) == null) {
                emailNotFoundResource.setMessage("Email not found");
                return new ResponseEntity<>(emailNotFoundResource, org.springframework.http.HttpStatus.OK);
            }
            requestHelp = "Cambio de contraseña";
            String subjectUrl = "http://localhost:4200/forgot-password?email=".concat(email);
            emailService.sendMessageForgotPassword(email, requestHelp, subjectUrl);
        } else if (emailResource.getMessageTypeEnum() == MessageType.CONTACT) {
            requestHelp = "Ayuda contacto para " + emailResource.getName();
            emailService.sendHtmlEmail(emailResource.getName(), emailResource.getEmail(), emailResource.getPhone(), emailResource.getMessage(), requestHelp);
        }


        emailNotFoundResource.setMessage("Email sent");

        return new ResponseEntity<>(emailNotFoundResource, org.springframework.http.HttpStatus.OK);
    }
}