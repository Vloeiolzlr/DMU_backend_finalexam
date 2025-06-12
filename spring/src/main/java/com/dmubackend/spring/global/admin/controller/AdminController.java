package com.dmubackend.spring.global.admin.controller;


import com.dmubackend.spring.domain.user.entity.User;
import com.dmubackend.spring.global.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> users = adminService.userFindAll();
        model.addAttribute("users", users);
        return "borders/admin";
    }
}
