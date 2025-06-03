package com.dmubackend.spring.token.service;

import com.dmubackend.spring.token.entity.Token;
import com.dmubackend.spring.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    public String generateOneTimePostLink(Long postId) {
        String token = UUID.randomUUID().toString();

        Token accessToken = new Token();
        accessToken.setToken(token);
        accessToken.setPostId(postId);
        tokenRepository.save(accessToken);

        return "http://localhost:8080/public/post/" + postId+"?token="+token;
    }
}
