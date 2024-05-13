package com.example.OceanNews.Procedure;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface EmailService {
    void loginVerification(String to, String subject, String text,String device,String location) throws IOException;
    String sendConfirmationEmail(String to,String token)throws MessagingException;
    void sendPasswordResetEmail(String to,String token)throws MessagingException;
    void sendEmail(String to,String subject,String text)throws MessagingException;
    void sendEmailToMultipleRecipients(String[] to,String subject,String text,String imagePath)throws MessagingException;

}
