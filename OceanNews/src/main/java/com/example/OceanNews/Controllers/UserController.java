package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import com.example.OceanNews.Serivices.ImpService.UserImpService;
import com.example.OceanNews.Serivices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService = new UserImpService();


    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        if (userService.userLogin(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok("Login successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/role/{role}")
    public Iterable<User> getUserByRole(@PathVariable Role role) {
     // (ResponseEntity) userService.findByRoles(role);
        return userService.findByRoles(role);
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
    @PatchMapping("/user/resetPassword/{id}")
    public void resetPassword(@PathVariable Long id,@RequestBody User user){
        userService.resetPassword(id,user.getEmail(),user.getPassword());
    }
}
