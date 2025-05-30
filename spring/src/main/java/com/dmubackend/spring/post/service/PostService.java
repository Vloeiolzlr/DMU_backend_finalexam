package com.dmubackend.spring.post.service;

import com.dmubackend.spring.post.entity.Post;
import com.dmubackend.spring.post.repository.PostRepository;
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
