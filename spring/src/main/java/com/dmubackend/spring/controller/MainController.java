package com.dmubackend.spring.controller;

import com.dmubackend.spring.dto.TrialDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "borders/index";
    }

    @GetMapping("/breads")
    public String breads() {
        return "borders/breads";
    }

    @GetMapping("/order")
    public String order() {
        return "borders/order";
    }
    @GetMapping("/login")
    public String login() {
        return "borders/login";
    }
    @PostMapping("/trial")
    public String trial(TrialDTO trialDto) {
        System.out.println(trialDto);
        return "";
    }
    @GetMapping("/post")
    public String post() {
        return "borders/blog";
    }
}
