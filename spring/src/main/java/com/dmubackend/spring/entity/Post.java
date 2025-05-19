package com.dmubackend.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String mainContent;

    private String breadContent;

    private String ovenContent;

    private String opusContent;

    private String teaContent;

    private String otherContent;

    private String otherTitle;

    private String otherMainContent;

}
