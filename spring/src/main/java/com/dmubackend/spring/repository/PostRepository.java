package com.dmubackend.spring.repository;

import com.dmubackend.spring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
