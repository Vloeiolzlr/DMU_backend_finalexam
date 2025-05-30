package com.dmubackend.spring.trialUser.controller;

import com.dmubackend.spring.trialUser.service.TrialUserService;
import com.dmubackend.spring.trialUser.dto.TrialDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TrialController {
    private final TrialUserService trialUserService;

    @PostMapping("/trial")
    @ResponseBody
    public ResponseEntity<?> trial(TrialDTO trialDto) {
        return trialUserService.save(trialDto);
    }
}
