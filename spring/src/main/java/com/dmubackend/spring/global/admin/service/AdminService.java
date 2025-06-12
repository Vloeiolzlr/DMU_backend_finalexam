package com.dmubackend.spring.global.admin.service;

import com.dmubackend.spring.domain.user.entity.User;
import com.dmubackend.spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    public List<User> userFindAll() {
        return userRepository.findAll();
    }
}
