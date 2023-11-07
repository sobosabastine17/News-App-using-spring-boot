package com.example.OceanNews.Controllers;

import com.example.OceanNews.DTO.JwtResponse;
import com.example.OceanNews.DTO.LoginRequest;
import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.User;
import com.example.OceanNews.Procedure.JwtTokenUtil;
import com.example.OceanNews.Serivices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static final Map<String, String> userStore = new HashMap<>();
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Add some test users to the user store (for demonstration purposes)
    static {
        userStore.put("user1", passwordEncoder.encode("password1"));
        userStore.put("user2", passwordEncoder.encode("password2"));
    }

//    @PostMapping("/user/login")
//    ResponseEntity<User>loginUser(@RequestBody User user){
//        User existingUser = userService.findUserByUsername(user.getUsername());
//        User existingPass = userService.findUserByPassword(user.getPassword());
//
//
//       // User existingPass = userService.findUserByPassword(user.getPassword());
//        if (existingUser == null) {
//            throw new ELException("User does not exist");
//        }
//        if (!user.getPassword().equals(existingPass.getPassword())) {
//            throw new ELException("Password is incorrect");
//        }
//        User tokenMap= generateJWTToken(existingUser);
//        return ResponseEntity.ok(tokenMap);
//    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String providedPassword = loginRequest.getPassword();

        if (userStore.containsKey(username)) {
            String storedPassword = userStore.get(username);

            if (passwordEncoder.matches(providedPassword, storedPassword)) {
                // Authentication successful
                // Generate a JWT token for the user
                String jwtToken = JwtTokenUtil.generateToken(username);

                // Return the token as part of the response
                return ResponseEntity.ok(new JwtResponse(jwtToken));
            }
        }

        // Authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new ELException("Username already exists");
        }
        User existingEmail = userService.findUserByUsername(user.getEmail());
        if (existingEmail != null) {
            throw new ELException("Email already exists");
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(JwtTokenUtil.generateToken(String.valueOf(newUser)));
    }
    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User existingUser = userService.findUserById(id);
        if (existingUser!=null) {
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/user/update/{id}")
    public void updateUser(
            @PathVariable Long id,
            @RequestParam(required=false) String username,
            @RequestParam(required=false) String email){
        userService.updateUser(id,username,email);
    }

    @DeleteMapping("/user/delete/{id}")
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
    @PatchMapping("/user/resetPassword/{email}/{password}")
    public ResponseEntity<String> resetPassword(@PathVariable String email,
                                                @PathVariable String password
    ) {
        Boolean existingUser=userService.existedByEmail(email);
        if (existingUser.equals(true)) {
            userService.resetPassword(email, password);
            return ResponseEntity.ok("password successfully reset");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email not found");
        }
    }
}
