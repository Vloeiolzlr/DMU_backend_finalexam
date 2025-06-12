package com.dmubackend.spring.domain.post.service;

import com.dmubackend.spring.domain.post.entity.Post;
import com.dmubackend.spring.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    public String showPost(Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "borders/blog";
    }

    public String showList(Model model) {
        List<Post> breads = postRepository.findAll();
        model.addAttribute("breads", breads);
        return "borders/breads";
    }
}
