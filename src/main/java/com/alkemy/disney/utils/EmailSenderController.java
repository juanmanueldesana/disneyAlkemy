package com.alkemy.disney.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;
    
    @GetMapping("/sendEmail")
    public String sendEmail(String email){


        return emailSenderService.sendEmail(email);
    }
}
