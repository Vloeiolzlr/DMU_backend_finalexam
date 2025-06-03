package com.dmubackend.spring.globalController;

import com.dmubackend.spring.user.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute
    public void addPrincipalToModel(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("principal", user);
        }
    }

}
