package com.dmubackend.spring.repository;

import com.dmubackend.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<User, Long> {
}
