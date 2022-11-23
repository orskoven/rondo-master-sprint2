package com.example.rondobackend.model.Mail;

import lombok.Getter;
import lombok.Setter;


//skal ikke gemmes i databasen
@Getter
@Setter
public class Mail {
    private String name;
    private String subject;
    private String message;
    private String sendFromMail;
}
