package com.dmubackend.spring.bread.service;


import com.dmubackend.spring.bread.entity.Bread;
import com.dmubackend.spring.bread.repository.BreadRepository;
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
