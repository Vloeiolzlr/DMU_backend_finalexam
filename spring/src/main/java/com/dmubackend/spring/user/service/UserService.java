package com.dmubackend.spring.user.service;

import com.dmubackend.spring.mail.service.MailService;
import com.dmubackend.spring.user.dto.UserDTO;
import com.dmubackend.spring.user.entity.User;
import com.dmubackend.spring.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> save(UserDTO userDTO) throws MessagingException, IOException {
        User newUser = new User();
        User existUser = userRepository.findByEmail(userDTO.getEmail());
        if (existUser == null) {
            newUser.setEmail(userDTO.getEmail());
            newUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
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


    public ResponseEntity<?> login(UserDTO userDTO, HttpServletRequest request) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(),
                            userDTO.getPassword()
                    )
            );
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);


            User user = userRepository.findByEmail(userDTO.getEmail());

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", user.getName() + "님, 반갑습니다."
            ));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "success", false,
                            "message", "이메일 또는 비밀번호가 올바르지 않습니다."
                    ));

        }

    }
}
