package com.dmubackend.spring.domain.user.repository;

import com.dmubackend.spring.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
