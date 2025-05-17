package com.dmubackend.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TrialDTO {
    private String name;
    private String email;
    private String message;
}
