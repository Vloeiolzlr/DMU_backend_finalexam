package com.dmubackend.spring.user.controller;


import com.dmubackend.spring.user.dto.UserDTO;
import com.dmubackend.spring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/order")
    public String order() {
        return "borders/order";
    }

    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity<?> save(UserDTO userDTO) {
        return userService.save(userDTO);
    }
}
