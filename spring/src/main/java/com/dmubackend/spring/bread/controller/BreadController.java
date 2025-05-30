package com.dmubackend.spring.bread.controller;

import com.dmubackend.spring.bread.service.BreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BreadController {
    private final BreadService breadService;
    @GetMapping("/breads")
    public String breads(Model model) {
        return breadService.show(model);
    }
}
