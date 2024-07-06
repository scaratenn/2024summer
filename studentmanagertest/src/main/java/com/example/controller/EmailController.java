package com.example.controller;

import com.example.EmaiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.*;


//import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/email")

public class EmailController {
    @Autowired
    private EmaiService emaiService;
    @GetMapping("text")
    public String text(){
        log.info("链接成功");
        return "成功";
    }
    @GetMapping("/sendTextMail")
    public void sendTextMail(@RequestParam String to, @RequestParam String subject,@RequestParam String text){
        emaiService.sendTextMailMessage(to,subject,text);
    }

    }
