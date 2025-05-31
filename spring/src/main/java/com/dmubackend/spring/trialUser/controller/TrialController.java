package com.dmubackend.spring.trialUser.controller;

import com.dmubackend.spring.trialUser.service.TrialUserService;
import com.dmubackend.spring.trialUser.dto.TrialDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TrialController {
    private final TrialUserService trialUserService;

    @PostMapping("/trial")
    @ResponseBody
    public ResponseEntity<?> trial(TrialDTO trialDto) throws MessagingException, IOException {
        return trialUserService.save(trialDto);
    }

}
