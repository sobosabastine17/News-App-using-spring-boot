package com.example.OceanNews.Procedure;

import com.example.OceanNews.Modules.User.Repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImplementation implements EmailService{
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private Environment environment;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void loginVerification(String to, String subject, String text,String device,String location) throws IOException {
        try {
            // Create a MIME message
            MimeMessage message = emailSender.createMimeMessage();

            // Enable multipart content for the message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

       // SimpleMailMessage message = new SimpleMailMessage();
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            // Create HTML content with embedded image
            String htmlContent ="<html><body><h1>"+subject+"!</h1>"
                    + "<p>"+text+"</p>"
                    + "<p>Account Login with this device: <b>"+device+"</b></p>"
                    + "<p>From this location: <b>"+location+"</b></p>"
                    + "<img src='cid:mailImage' alt='Mail Image'></body></html>";

            helper.setText(htmlContent, true);

            // Load the image from the correct path
            Resource resource = new ClassPathResource("images/mail/footer.png");
            helper.addInline("mailImage", resource);

            emailSender.send(message);
    } catch (MessagingException e) {
        e.printStackTrace();
        // Handle exception
    }
    }

    @Override
    public String sendConfirmationEmail(String to,String token) throws MessagingException {

        String callbackLink ="http://localhost:8080/api/newsApp/v1/user/confirm?token=" + token;

        // Create an HTML message instead of a plain text message
        String htmlMessage = "<html><body>"
                + "<p>Click the link below to confirm:</p>"
                + "<a href=\"" + callbackLink + "\">Confirm Email</a>"
                + "</body></html>";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject("Confirmation Email");
        helper.setText(htmlMessage, true);
        emailSender.send(message);
        return "Email sent successfully";
    }

    @Override
    public void sendPasswordResetEmail(String to, String token) throws MessagingException {
        String callbackLink = "http://localhost:8080/api/newsApp/v1/user/reset?token=" + token;

        // Create an HTML message instead of a plain text message
        String htmlMessage = "<html><body>"
                + "<p>Click the link below to reset your password:</p>"
                + "<a href=\"" + callbackLink + "\">Reset Password</a>"
                + "</body></html>";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject("Password Reset Email");
        helper.setText(htmlMessage, true);
        emailSender.send(message);
    }

    @Override
    public void sendEmail(String to, String subject, String text) throws MessagingException {
        //send password reset success email
        try {
            // Create a MIME message
            MimeMessage message = emailSender.createMimeMessage();

            // Enable multipart content for the message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // SimpleMailMessage message = new SimpleMailMessage();
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            // Create HTML content with embedded image
            String htmlContent ="<html><body><h1>"+subject+"!</h1>"
                    + "<p>"+text+"</p>"
                    +" <p>Please if the reset password was not done by you, kindly inform us</p>"
                    + "<img src='cid:mailImage' alt='Mail Image'></body></html>";

            helper.setText(htmlContent, true);

            // Load the image from the correct path
            Resource resource = new ClassPathResource("images/mail/footer.png");
            helper.addInline("mailImage", resource);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @Override
    public void sendEmailToMultipleRecipients(String[] to, String subject, String text, String imagePath) throws MessagingException {
        //send password reset success email
        try {
            // Create a MIME message
            MimeMessage message = emailSender.createMimeMessage();

            // Enable multipart content for the message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // SimpleMailMessage message = new SimpleMailMessage();
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            // Create HTML content with embedded image
            String htmlContent ="<html><body><h1>"+subject+"!</h1>"
                    + "<p>"+text+"</p>"
                    + "<img src='cid:mailImage' alt='Mail Image'></body></html>";

            helper.setText(htmlContent, true);

            // Load the image from the correct path
            Resource resource = new ClassPathResource(imagePath);
            helper.addInline("mailImage", resource);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
