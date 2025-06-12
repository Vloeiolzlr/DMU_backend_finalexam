package com.dmubackend.spring.domain.trialUser.repository;

import com.dmubackend.spring.domain.trialUser.entity.TrialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrialUserRepository extends JpaRepository<TrialUser, Long> {
    TrialUser findByEmail(String email);
}
