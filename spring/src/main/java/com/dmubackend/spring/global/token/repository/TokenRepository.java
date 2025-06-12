package com.dmubackend.spring.global.token.repository;

import com.dmubackend.spring.global.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByPostIdAndTokenAndUsedFalse(Long postId, String token);
}
