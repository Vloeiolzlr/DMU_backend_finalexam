package com.dmubackend.spring.global.homeController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "borders/index";
    }
    
    // 메일 템플릿 확인
    @GetMapping("/mail")
    public String mail() {
        return "mail/mail";
    }
}
