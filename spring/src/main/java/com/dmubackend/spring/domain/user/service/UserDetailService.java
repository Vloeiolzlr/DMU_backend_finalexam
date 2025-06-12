package com.dmubackend.spring.domain.user.service;

import com.dmubackend.spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(userRepository.findByEmail(email) == null) {
            throw new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다: ");
        }
        return userRepository.findByEmail(email);
    }

}
