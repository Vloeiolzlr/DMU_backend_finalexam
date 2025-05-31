package com.dmubackend.spring.user.service;

import com.dmubackend.spring.mail.service.MailService;
import com.dmubackend.spring.user.dto.UserDTO;
import com.dmubackend.spring.user.entity.User;
import com.dmubackend.spring.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MailService mailService;

    public ResponseEntity<?> save(UserDTO userDTO) throws MessagingException, IOException {
        User newUser = new User();
        User existUser = userRepository.findByEmail(userDTO.getEmail());
        if (existUser == null) {
            newUser.setEmail(userDTO.getEmail());
            newUser.setName(userDTO.getName());
            newUser.setSubscribe(false);
            userRepository.save(newUser);
            mailService.sendMailUser(newUser.getEmail());
            return ResponseEntity.ok(Map.of("success", true, "message", newUser.getName()+ "님의 빵이 곧 도착합니다"));
        }
        else if(existUser.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("success", false, "message", "이미 구독중인 사용자입니다 ! 기한이 끝나고 신청해주세요."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("success", false, "message", "요청을 처리할 수 없습니다."));

    }
}
