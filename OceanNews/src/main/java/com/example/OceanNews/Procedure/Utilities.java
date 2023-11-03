package com.example.OceanNews.Procedure;

import java.util.regex.Pattern;

public class Utilities {
    public static boolean isValidEmail(String email) {
        // Define a regex pattern for a valid email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Define a regex pattern for a valid phone number format
        // Example pattern: ^\d{10}$ for 10-digit numbers
        String phoneRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }
}
