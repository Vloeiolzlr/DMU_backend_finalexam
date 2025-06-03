package com.dmubackend.spring.post.controller;

import com.dmubackend.spring.post.service.PostService;
import com.dmubackend.spring.token.entity.Token;
import com.dmubackend.spring.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final TokenRepository tokenRepository;
    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        return postService.showPost(id, model);
    }

    @GetMapping("/public/post/{id}")
    public String publicPost(@PathVariable Long id, @RequestParam String token, Model model) {
        Optional<Token> loadToken = tokenRepository.findByPostIdAndTokenAndUsedFalse(id, token);
        if (loadToken == null || loadToken.isEmpty()) {
            return "/post/"+id;
        }
        Token accessToken = loadToken.get();
        accessToken.setUsed(true);
        tokenRepository.save(accessToken);

        return postService.showPost(id, model);
    }

    @GetMapping("/breads")
    public String breads(Model model) {
        return postService.showList(model);
    }
}
