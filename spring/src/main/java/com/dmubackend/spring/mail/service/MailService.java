package com.dmubackend.spring.mail.service;


import com.dmubackend.spring.post.entity.Post;
import com.dmubackend.spring.post.repository.PostRepository;
import com.dmubackend.spring.trialUser.entity.TrialUser;
import com.dmubackend.spring.trialUser.repository.TrialUserRepository;
import com.dmubackend.spring.user.entity.User;
import com.dmubackend.spring.user.repository.UserRepository;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void sendMailUser() throws MessagingException, IOException {

        // 콘텐츠 생성된 순서 (id) 및 보내지 않은 순서로 정리
        Optional<Post> optionalPost = postRepository.findFirstBySentAtIsNullOrderByIdAsc();

        if(optionalPost.isEmpty()) { // DB에 있는 콘텐츠가 발송이 됐을 경우
            System.out.print("모든 구독 메일이 발송됨");
            return;
        }

        Post post = optionalPost.get();

        MustacheFactory mf = new DefaultMustacheFactory();
        Reader template = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("templates/mail/mail.mustache"), StandardCharsets.UTF_8);
        Mustache mustache = mf.compile(template, "mail.mustache");

        List<User> users = userRepository.findAll();

        for(User user : users) {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(user.getEmail()); // 수신자
            helper.setSubject("주문하신 빵이 도착했습니다."); // 메일 제목
            helper.setFrom("vloeiolzlr@gmail.com"); // 서버 환경설정에서 정의한 보내는 사람 정보



            StringWriter writer = new StringWriter();
            mustache.execute(writer, Map.of("link", "http://localhost:8080/post/"+post.getId(), // 메일 템플릿 텍스트 삽입
                    "breadName", post.getBreadName(),
                    "title", post.getTitle())).flush();
            String html = writer.toString();


            helper.setText(html, true);

            File image = new ClassPathResource("static/images/breadAria1.png").getFile(); //이미지 파일
            helper.addInline("logo", image);

            image = new ClassPathResource("/static/images/music_note.png").getFile(); // 이미지 파일
            helper.addInline("music_note", image);

            mailSender.send(mimeMessage); // 메일 발송

        }



        post.setSentAt(LocalDateTime.now()); // DB에 보낸여부 설정
        postRepository.save(post); // DB 업데이트
    }

}
