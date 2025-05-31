package com.dmubackend.spring.mail.controller;


import com.dmubackend.spring.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;


    // 임시 컨트롤러
    @GetMapping("/sendmailuser")
    public ResponseEntity<String> sendmailuser() {
        try {
            mailService.sendMailUser(null);
            return ResponseEntity.ok("성공");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("실패");
        }
    }

}
