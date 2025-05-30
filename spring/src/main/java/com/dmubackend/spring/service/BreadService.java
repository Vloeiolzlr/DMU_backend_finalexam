package com.dmubackend.spring.service;


import com.dmubackend.spring.entity.Bread;
import com.dmubackend.spring.repository.BreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreadService {
    private final BreadRepository breadRepository;

    public String show(Model model) {
        List<Bread> breads = breadRepository.findAll();
        model.addAttribute("breads", breads);
        return "borders/breads";
    }
}
