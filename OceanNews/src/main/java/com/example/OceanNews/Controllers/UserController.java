package com.example.OceanNews.Controllers;

import com.example.OceanNews.Constants;
import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.User;
import com.example.OceanNews.Serivices.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.crypto.Cipher.SECRET_KEY;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    ResponseEntity<User>loginUser(@RequestBody User user){
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser == null) {
            throw new ELException("User does not exist");
        }
        if (!user.getPassword().equals(existingUser.getPassword())) {
            throw new ELException("Password is incorrect");
        }
        User tokenMap= generateJWTToken(existingUser);
        return ResponseEntity.ok(tokenMap);
    }
    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new ELException("Username already exists");
        }
        User existingEmail = userService.findUserByUsername(user.getEmail());
        if (existingEmail != null) {
            throw new ELException("Email already exists");
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(generateJWTToken(newUser));
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


    //*************************************//
    //....end of user Mapping Request...
    //*************************************//

    private User generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.Token_Validity))
                .claim("id", user.getUserID())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .compact();
        User tokenMap = new User();
        tokenMap.put("token", token);
        return tokenMap;
    }
}
