package com.dmubackend.spring.post.controller;

import com.dmubackend.spring.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        return postService.show(id, model);
    }
}
