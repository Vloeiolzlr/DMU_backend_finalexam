package com.dmubackend.spring.controller;

import com.dmubackend.spring.dto.TrialDTO;
import com.dmubackend.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/trial")
    public String trial(TrialDTO trialDto, Model model) {
        userService.save(trialDto, model);
        return "redirect:/";
    }

}
