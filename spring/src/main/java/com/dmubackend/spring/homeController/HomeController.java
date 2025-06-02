package com.dmubackend.spring.homeController;

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


    @GetMapping("/login")
    public String login() {
        return "borders/login";
    }



    
    // 메일 템플릿 확인
    @GetMapping("/mail")
    public String mail() {
        return "mail/mail";
    }
}
