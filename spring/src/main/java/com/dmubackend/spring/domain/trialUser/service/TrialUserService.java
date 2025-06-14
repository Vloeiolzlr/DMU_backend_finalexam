package com.dmubackend.spring.domain.trialUser.service;

import com.dmubackend.spring.domain.mail.service.MailService;
import com.dmubackend.spring.domain.trialUser.dto.TrialDTO;
import com.dmubackend.spring.domain.trialUser.entity.TrialUser;
import com.dmubackend.spring.domain.trialUser.repository.TrialUserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrialUserService {
    private final TrialUserRepository repository;
    private final MailService mailService;

    public ResponseEntity<?> save(TrialDTO trialDto) throws MessagingException, IOException {
        TrialUser newUser = new TrialUser();
        TrialUser existUser = repository.findByEmail(trialDto.getEmail());
        if(existUser == null) {
            newUser.setName(trialDto.getName());
            newUser.setEmail(trialDto.getEmail());
            repository.save(newUser);
            mailService.sendMailUser(trialDto.getEmail());
            return ResponseEntity.ok(Map.of("success", true, "message", newUser.getName()+ "님의 빵이 곧 도착합니다"));
        }
        else if(existUser.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("success", false, "message", "체험판은 한 번만 제공되며, 고객님께서는 이미 이용해주셨습니다"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("success", false, "message", "요청을 처리할 수 없습니다."));

    }
}
