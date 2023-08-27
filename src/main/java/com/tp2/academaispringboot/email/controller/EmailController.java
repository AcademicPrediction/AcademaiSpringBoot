package com.tp2.academaispringboot.email.controller;

import com.tp2.academaispringboot.email.dto.EmailResource;
import com.tp2.academaispringboot.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailResource emailResource) {

        String requestHelp = "Ayuda para " + emailResource.getEmail() + " " + emailResource.getName() + " " + emailResource.getPhone();

        emailService.sendSimpleMessage("academaitesis@outlook.com", requestHelp, emailResource.getMessage());
        return "Email sent";
    }
}