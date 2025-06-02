package com.dmubackend.spring.mail.scheduler;

import com.dmubackend.spring.mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MailScheduler {
    private final MailService mailService;

   /* @Scheduled(cron = "0 * * * * *")
    public void mailScheduler() throws MessagingException, IOException {
        mailService.sendMailUser(null);
    }*/
}
