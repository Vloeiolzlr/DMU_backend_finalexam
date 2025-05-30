package com.dmubackend.spring.post.repository;

import com.dmubackend.spring.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
