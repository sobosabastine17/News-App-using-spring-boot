package com.example.OceanNews.Procedure;


import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import net.coobird.thumbnailator.Thumbnails;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Setter
@Getter
@Component
public class Utilities {
    private HttpServletRequest request;
    private static final long MAX_IMAGE_SIZE_BYTES = 2 * 1024 * 1024; // 2MB max size
    private static final String[] ALLOWED_IMAGE_EXTENSIONS = {"jpg", "jpeg", "png", "gif", "webp"};
    @Value("${upload.path}")
    private String uploadPath;
    // Get the current date and time with seconds and microseconds
    static LocalDateTime now = LocalDateTime.now();

    // Define a custom date-time formatter to include seconds and microseconds
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    // Format the LocalDateTime object using the custom formatter
    static String formattedDateTime = now.format(formatter);
    private static final String FIXED_SALT = "$2a$10$abcdefghijklmnopqrstuu";

    //Generate 8 characters password
    public static String generateRandomChar() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public static String uniqueNumber() {
        // Generate a unique number using the current timestamp
        return System.currentTimeMillis() + generateRandomChar() + formattedDateTime;
    }

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
        //check if password should be greater than 8 characters
        return password.length() >= 8;
    }
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

    //Ato generate password of 8 characters
    public static String generatePassword() {
        return generateToken().substring(0, 8);
    }
    // Method to generate a verification link

    public static boolean isNull(Object object) {
        return object == null;

    }

    //get the IP address of the user
    public static String getUserIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    // You can use third-party services or libraries to get user location based on IP address.
    // Here's a simple example using a hypothetical getLocationFromIpAddress method:
    public static String getUserLocation(String ipAddress) {
        String location = getLocationFromIpAddress(ipAddress);
        System.out.println("Location: " + location);
        // System.out.println("IP Address: " + getUserIpAddress());
        return location;
    }

    private static String getLocationFromIpAddress(String ipAddress) {
        // Implementation depends on the service or library you use
        // Example: Call a web service that provides location information based on IP address
        return "Location information for IP address: " + ipAddress;
    }

    public static String isValidImage(MultipartFile file) {
        // Check file size
        if (file.getSize() > MAX_IMAGE_SIZE_BYTES) {
            return "File size exceeds the limit of 2MB. Please upload a smaller image.";
        }
        // Check file extension
        String originalFileName = file.getOriginalFilename();
        if (originalFileName != null) {
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
            for (String allowedExtension : ALLOWED_IMAGE_EXTENSIONS) {
                if (fileExtension.equals(allowedExtension)) {
                    return "File extension is not allowed. Please upload an image with one of the following extensions: jpg, jpeg, png, gif, webp.";
                }
            }
        }
        return originalFileName;
    }

    public static String compressAndSaveImage(MultipartFile file, Path path) throws IOException {
        isValidImage(file);
        if (file.getSize() <= MAX_IMAGE_SIZE_BYTES) {
            // If file size is within the limit, save without compression
            Files.write(path, file.getBytes());
        } else {
            // Generate a unique file name using UUID
            String uniqueFileName = hashPassword(generatePassword() + uniqueNumber()) + "_" + file.getOriginalFilename();

            // Create the full path including the unique file name
            Path uniquePath = Paths.get(path.getParent().toString(), uniqueFileName);

            // Compress and save the image to the unique path
            Thumbnails.of(file.getInputStream())
                    .size(1024, 1024) // Resize to 1024x1024 (adjust as needed)
                    .outputQuality(0.8) // Reduce quality to 80% (adjust as needed)
                    .toFile(uniquePath.toFile());
            return uniquePath.toString();
        }
        return null;
    }
}
