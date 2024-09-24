//package com.nexjot.nexjot.api.service;
//
//import jakarta.validation.constraints.Email;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//@Getter
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//
//    @Autowired
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendEmail(String to, String subject, String body) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setFrom("barryakanss@gmail.com");
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(body);
//
//        mailSender.send(mailMessage);
//    }
//}
