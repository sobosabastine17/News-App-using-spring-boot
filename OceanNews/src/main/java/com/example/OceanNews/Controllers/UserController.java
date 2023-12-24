package com.example.OceanNews.Controllers;

import com.example.OceanNews.DTO.Auth.AuthenticationRequest;
import com.example.OceanNews.DTO.Auth.AuthenticationResponse;
import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import com.example.OceanNews.Repo.UserRepo;
import com.example.OceanNews.Serivices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
//        if (userService.userLogin(request.getEmail(),request.getPassword(),request)) {
//            return ResponseEntity.ok("Login successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
         return ResponseEntity.ok(userService.userLogin(request.getEmail(),request.getPassword(),request));
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
    public void update(
            @PathVariable Long id,
            @RequestBody User user ) {
        userService.update(id,user);
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
