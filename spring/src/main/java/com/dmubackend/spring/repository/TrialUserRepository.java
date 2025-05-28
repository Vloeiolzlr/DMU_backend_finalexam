package com.dmubackend.spring.repository;

import com.dmubackend.spring.entity.TrialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrialUserRepository extends JpaRepository<TrialUser, Long> {
    TrialUser findByEmail(String email);
}
