package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.User;
import com.example.OceanNews.Serivices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

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

    //user login
    @GetMapping("/user/login/{user}/{password}")
    public ResponseEntity<String> userLogin(@PathVariable String user,
                                            @PathVariable String password
    ) {
        Boolean existingEmail=userService.existedByEmail(user);
        Boolean existingUser=userService.existsByUsername(user);
        Boolean existingPassword=userService.existedByPassword(password);
        User existsUser= userService.findUserById(1L);
        if (existingUser.equals(true) || existingEmail.equals(true) && existingPassword.equals(true)) {
            return ResponseEntity.ok("login successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("details not found");
        }
    }

    //*************************************//
    //....end of user Mapping Request...
    //*************************************//
}
