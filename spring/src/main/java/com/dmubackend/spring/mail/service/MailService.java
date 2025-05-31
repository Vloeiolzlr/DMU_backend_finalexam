package com.dmubackend.spring.mail.service;


import com.dmubackend.spring.post.entity.Post;
import com.dmubackend.spring.post.repository.PostRepository;
import com.dmubackend.spring.user.entity.User;
import com.dmubackend.spring.user.repository.UserRepository;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
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

    public void sendMailUser(String email) throws MessagingException, IOException {

        Optional<Post> optionalPost = (email == null)
                ? postRepository.findFirstBySentAtIsNullOrderByIdAsc() // 이메일이 없으면 전체적으로 보내는 주기메일 발송
                : postRepository.findFirstBySentAtIsNotNullOrderByIdDesc(); // 이메일이 있으면 방금 구독한 사람에게만 발송할 메일

        Post post = optionalPost.get();

        // 템플릿 준비
        MustacheFactory mf = new DefaultMustacheFactory();
        Reader template = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("templates/mail/mail.mustache"), StandardCharsets.UTF_8);
        Mustache mustache = mf.compile(template, "mail.mustache");

        File logoImage = new ClassPathResource("static/images/breadAria1.png").getFile();
        File noteImage = new ClassPathResource("static/images/music_note.png").getFile();

        if(email == null) { // 구독 사용자들에게 주기메일 발송

            List<User> users = userRepository.findAll();

            for (User user : users) {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                // 수신자
                helper.setSubject("주문하신 빵이 도착했습니다."); // 메일 제목
                helper.setFrom("vloeiolzlr@gmail.com"); // 서버 환경설정에서 정의한 보내는 사람 정보
                helper.setTo(user.getEmail());

                StringWriter writer = new StringWriter();
                mustache.execute(writer, Map.of(
                        "link", "http://localhost:8080/post/" + post.getId(), // 메일 템플릿 텍스트 삽입
                        "breadName", post.getBreadName(),
                        "title", post.getTitle()
                )).flush();

                helper.setText(writer.toString(), true);
                helper.addInline("logo", logoImage);
                helper.addInline("music_note", noteImage);

                mailSender.send(mimeMessage); // 메일 발송
            }
            post.setSentAt(LocalDateTime.now()); // DB에 보낸여부 설정
            postRepository.save(post); // DB 업데이트

        }
        else { // 방금 구독한 사용자에겐 이번주에 발송된 메일 재발송
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setSubject("주문하신 빵이 도착했습니다."); // 메일 제목
            helper.setFrom("vloeiolzlr@gmail.com"); // 서버 환경설정에서 정의한 보내는 사람 정보
            helper.setTo(email);

            StringWriter writer = new StringWriter();
            mustache.execute(writer, Map.of(
                    "link", "http://localhost:8080/post/" + post.getId(), // 메일 템플릿 텍스트 삽입
                    "breadName", post.getBreadName(),
                    "title", post.getTitle()
            )).flush();

            helper.setText(writer.toString(), true);
            helper.addInline("logo", logoImage);
            helper.addInline("music_note", noteImage);

            mailSender.send(mimeMessage);
        }


    }

}
