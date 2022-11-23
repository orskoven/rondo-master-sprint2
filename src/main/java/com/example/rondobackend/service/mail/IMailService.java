package com.example.rondobackend.service.mail;

import org.springframework.mail.SimpleMailMessage;

public interface IMailService {
    SimpleMailMessage sendMail(String sendFromMail, String subject, String message, String name);
    boolean validateEmail(String email);
}
