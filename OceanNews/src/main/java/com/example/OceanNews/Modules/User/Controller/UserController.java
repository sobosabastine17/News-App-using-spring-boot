package com.example.OceanNews.Modules.User.Controller;

import com.example.OceanNews.DTO.ResetPasswordDTO;
import com.example.OceanNews.Modules.Roles.Roles;
import com.example.OceanNews.Modules.User.User;
import com.example.OceanNews.Procedure.EmailService;
import com.example.OceanNews.Modules.User.Repository.UserRepository;
import com.example.OceanNews.Modules.User.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/newsApp/v1/user")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpServletRequest request) throws IOException, MessagingException {
        // Get IP address
        String ipAddress = request.getRemoteAddr();
        // Get device information (User-Agent header)
        String userAgent = request.getHeader("User-Agent");
        //checking if username or email exists
        if (!userService.existedByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("{\"message\": \"email or password does not exist\"}");
        }
        if (!userService.existedByPassword(user.getPassword())) {
            return ResponseEntity.badRequest().body("{\"message\": \"Pemail or password does not exist\"}");
        }
       //sending email
        try {
            String to = user.getEmail();
            String subject = "Alert";
            String text = "Please report if login was not made by you";
            emailService.loginVerification(to, subject, text, userAgent, ipAddress);
            return ResponseEntity.ok(userService.userLogin(user));
        }catch (Exception e){
            return ResponseEntity.ok(userService.userLogin(user));
        }

    }
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) throws MessagingException {
        // checking if username or email already exists
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("{\"message\": \"Username already exists\"}");
        }
        if (userService.existedByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("{\"message\": \"Email already exists\"}");
        }
        // check if writerName is null
        if (user.getWriterName() == null) {
            return ResponseEntity.badRequest().body("{\"message\": \"Writer name is required\"}");
        }

        return ResponseEntity.ok(String.valueOf(userService.userRegistration(user)));
    }
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
    @GetMapping("/role/{role}")
    public Iterable<User> getUserByRole(@PathVariable Roles role) {
     // (ResponseEntity) userService.findByRoles(role);
        return userService.findByRoles(role);
    }
    @PatchMapping("/update/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody User user ) {
        //checking
        userService.update(id,user);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        User existingUser = userService.findUserById(id);
        if (existingUser!=null) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    //reset password
    @PatchMapping("/resetPassword/{id}")
    public ResponseEntity resetPassword(@PathVariable Long id, @RequestBody ResetPasswordDTO user) {

        try {
            userService.resetPassword(id, user);
            User us = userService.findUserById(id);
            emailService.sendEmail(us.getEmail(),
                    "Password Reset",
                    "Your password has been reset successfully");
            return ResponseEntity.ok("Password reset successfully");
        } catch (Exception e) {
            userService.resetPassword(id, user);
            return ResponseEntity.ok("Password reset successfully");
        }
    }
    @GetMapping("/confirm")
    public ResponseEntity confirmStatus(@RequestParam("token")String token) throws MessagingException {
        //token = Utilities.generateToken();
        User user = userService.findByToken(token);
       if (user != null) {
           //check if token is expired
            user.setEmailVerified(true);
            return ResponseEntity.ok("Your Email Have Been Confirmed");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }
}
