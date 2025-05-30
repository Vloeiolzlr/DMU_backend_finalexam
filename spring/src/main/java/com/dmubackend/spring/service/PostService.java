package com.dmubackend.spring.service;

import com.dmubackend.spring.entity.Post;
import com.dmubackend.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    public String show(Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "borders/blog";
    }
}
