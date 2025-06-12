package com.dmubackend.spring.domain.post.repository;

import com.dmubackend.spring.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findFirstBySentAtIsNullOrderByIdAsc();
    Optional<Post> findFirstBySentAtIsNotNullOrderByIdDesc();
}
