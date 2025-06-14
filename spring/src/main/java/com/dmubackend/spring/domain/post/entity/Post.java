package com.dmubackend.spring.domain.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subTitle;

    private String img;

    private String breadName;

    private String breadContent;

    private String ovenTemp;

    private String ovenContent;

    private String opusContent;

    private String teaName;

    private String teaContent;


    private String otherTitle1;

    private String otherMainContent1;

    private String otherTitle2;

    private String otherMainContent2;

    private String mainLink;

    private String otherLink1;

    private String otherLink2;

    private LocalDateTime sentAt;
}
