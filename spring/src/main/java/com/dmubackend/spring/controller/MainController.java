package com.dmubackend.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String index() {
        return "borders/index";
    }

    @GetMapping("/breads")
    public String breads() {
        return "borders/breads";
    }

    @GetMapping("/login")
    public String login() {
        return "borders/login";
    }

    @GetMapping("/post")
    public String post() {
        return "borders/blog";
    }

    @GetMapping("/mail")
    public String mail() {
        return "mail/mail";
    }



}
