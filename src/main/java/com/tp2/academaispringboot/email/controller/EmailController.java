package com.tp2.academaispringboot.email.controller;

import com.tp2.academaispringboot.email.dto.EmailResource;
import com.tp2.academaispringboot.email.service.EmailService;
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

    @Operation(summary = "Send email", description = "Send email")
    @PostMapping(value = "/send-email",consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> sendEmail(@RequestBody EmailResource emailResource) {
        System.out.println(emailResource.toString());

        String requestHelp = "Cambio de contraseña";
        String subjectUrl = "http://localhost:4200/forgot-password".concat(emailResource.getEmail());

        emailService.sendSimpleMessage(emailResource.getEmail(), requestHelp, subjectUrl);
        return new ResponseEntity<>("Email sent", org.springframework.http.HttpStatus.OK);
    }
}