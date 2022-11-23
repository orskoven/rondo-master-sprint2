package com.example.rondobackend.controller.mail;

import com.example.rondobackend.service.mail.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MailControllerTest {
    private MockMvc mockMvc; //bruger både service og controller layer
    private MailService mailService;
    private final JavaMailSender mailSender;

    public MailControllerTest(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @BeforeEach
    public void setUp(){
        mailService = new MailService(mailSender);
        mockMvc = MockMvcBuilders.standaloneSetup(new MailController(mailService)).build();
    }


    @Test
    void sendMail() throws Exception {
        mockMvc.perform(post("/mail")
                        .contentType(MediaType.APPLICATION_JSON)//vi sender JSON fromat (til controlleren)
                        .content("{\"name\":\"gården og gaden\",\"subject\":\"ændring i odre\",\"message\":\"1 istedet for 2 brød\",\"sendFromMail\":\"rasm64n8@stud.kea.dk\"}")//Bygger Request boddyen op
                        .accept(MediaType.APPLICATION_JSON))//Mediatypen den(controlleren) modtager skal være JSON
                .andExpect(status().isOk())//vi regner med at statusen skal være OK
                .andDo(print());
    }
}