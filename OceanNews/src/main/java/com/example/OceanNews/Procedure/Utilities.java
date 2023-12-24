package com.example.OceanNews.Procedure;


import com.example.OceanNews.Model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    public static boolean isValidEmail(String email) {
        // Define a regex pattern for a valid email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return !pattern.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for a phone number starting with 0 and having 10 digits
        String regex = "^0\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return !matcher.matches();
    }

    // check if password is valid
    public static boolean isValidPassword(String password) {
        // Define a regex pattern for a valid password format
        //String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }

    //Password hashing using BCrypt
    private static final String FIXED_SALT = "$2a$10$abcdefghijklmnopqrstuu";

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, FIXED_SALT);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    //Generate token using UUID
    public static String generateToken() {
        return java.util.UUID.randomUUID().toString();
    }

    // Method to generate a verification token
    public static String generateVerificationToken() {
        return generateToken();
    }

    // Method to send the verification email
    public static void sendVerificationEmail(User user, String verificationLink) {
        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("Ocean News Hub");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Please verify your email");
        mailMessage.setText("Dear " + user.getUsername() + ",\n\n" +
                "Thank you for registering on our website. Please click on the below link to verify your email:\n\n" +
                verificationLink);
    }

}
