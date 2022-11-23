package com.example.rondobackend.service.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;

class MailServiceTest {
    private MailService mailService;
    private JavaMailSender mailSender;

    @BeforeEach
    public void setUp(){
        mailService = new MailService(mailSender);
    }

    @Test
    void validateEmail() {
        String mail1 = "rasm@gmail.com";
        String mail2 = "jsfeffoe";
        String mail3 = "hej.med.dig";

        Assertions.assertTrue(mailService.validateEmail(mail1));
        Assertions.assertFalse(mailService.validateEmail(mail2));
        Assertions.assertFalse(mailService.validateEmail(mail3));
    }

    @Test
    void sendMail() {
    }
}