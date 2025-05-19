package com.dmubackend.spring.service;

import com.dmubackend.spring.dto.TrialDTO;
import com.dmubackend.spring.entity.User;
import com.dmubackend.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void save(TrialDTO trialDto, Model model) {
        model.addAttribute("info", "처리 중입니다.");
        User newUser = new User();
        User existUser = repository.findByEmail(trialDto.getEmail());
        if(existUser == null) {
            newUser.setName(trialDto.getName());
            newUser.setEmail(trialDto.getEmail());
            newUser.setTrial(true);
            repository.save(newUser);
            model.addAttribute("info", newUser.getName()+"님의 빵이 곧 도착합니다");
        }
        else if(existUser.isTrial()) {
            model.addAttribute("info", "체험판은 한 번만 제공되며, 고객님께서는 이미 이용해주셨습니다");

        }
    }
}
