package com.tp2.academaispringboot.email.controller;

import com.tp2.academaispringboot.email.dto.EmailResource;
import com.tp2.academaispringboot.email.dto.EmailResponseResource;
import com.tp2.academaispringboot.email.service.EmailService;
import com.tp2.academaispringboot.supervisor.service.SupervisorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/send-email",consumes = "application/json", produces = "text/plain")
    public ResponseEntity<EmailResponseResource> sendEmail(@RequestBody EmailResource emailResource) {
        System.out.println(emailResource.toString());

        String requestHelp = "Cambio de contraseña";
        String subjectUrl = "http://localhost:4200/forgot-password?email=".concat(emailResource.getEmail());

        EmailResponseResource emailNotFoundResource = new EmailResponseResource();

        if (supervisorService.getSupervisorByEmail(emailResource.getEmail()) == null) {
            emailNotFoundResource.setMessage("Email not found");
            return new ResponseEntity<>(emailNotFoundResource, org.springframework.http.HttpStatus.NOT_FOUND);
        }

        emailService.sendSimpleMessage(emailResource.getEmail(), requestHelp, subjectUrl);
        emailNotFoundResource.setMessage("Email sent");

        return new ResponseEntity<>(emailNotFoundResource, org.springframework.http.HttpStatus.OK);
    }
}