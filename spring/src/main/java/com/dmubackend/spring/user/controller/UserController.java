package com.dmubackend.spring.user.controller;


import com.dmubackend.spring.user.dto.UserDTO;
import com.dmubackend.spring.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/profile")
    public String profile() {
        return "borders/profile";
    }

    @GetMapping("/order")
    public String order() {
        return "borders/order";
    }

    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity<?> save(UserDTO userDTO) throws MessagingException, IOException {
        return userService.save(userDTO);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "borders/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(UserDTO userDTO, HttpServletRequest request) {
        return userService.login(userDTO, request);
    }

    @PostMapping("/editInfo")
    @ResponseBody
    public ResponseEntity<?> editInfo(UserDTO userDTO) {
        return userService.editInfo(userDTO);
    }
}
