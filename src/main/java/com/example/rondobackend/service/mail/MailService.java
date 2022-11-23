package com.example.rondobackend.service.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class MailService implements IMailService{

    //en klasse der kan sende emailen (kommer fra dependency spring-boot-starter-mail)
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean validateEmail(String email){
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }

    @Override
    public SimpleMailMessage sendMail(String sendFromMail, String subject, String message, String name) {

        if (validateEmail(sendFromMail) == true) {
            //en klasse der kan opbygge mailen (kommer fra dependency spring-boot-starter-mail)
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //emailen bliver sendt fra
            simpleMailMessage.setFrom(sendFromMail);
            //emailen bliver sendt til - skal ændre til rondos mail
            simpleMailMessage.setTo("rswoldike@gmail.com");
            //email subject
            simpleMailMessage.setSubject(subject);
            //selve beskeden
            simpleMailMessage.setText("Besked sendt fra: " + name + "\n" + message);

            this.mailSender.send(simpleMailMessage);
            return simpleMailMessage;
        } else {
            System.out.println("mail ikke valid");
            return null;
        }
    }
}
