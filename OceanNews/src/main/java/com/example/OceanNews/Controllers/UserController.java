package com.example.OceanNews.Controllers;

import com.example.OceanNews.DTO.Auth.AuthenticationRequest;
import com.example.OceanNews.DTO.Auth.AuthenticationResponse;
import com.example.OceanNews.DTO.Auth.RegistrationRequest;
import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import com.example.OceanNews.Serivices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/newsApp/v1")
public class UserController {
    private final UserService userService;
    @PostMapping("/user/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
         return ResponseEntity.ok(userService.userLogin(request));
    }
    @PostMapping("/user/create")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegistrationRequest user) {
        return ResponseEntity.ok(userService.saveUser(user));
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
