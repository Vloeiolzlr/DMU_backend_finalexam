package com.dmubackend.spring.controller;

import com.dmubackend.spring.dto.TrialDTO;
import com.dmubackend.spring.service.TrialUserService;
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
