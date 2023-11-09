package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findByUsername(String username);
    Boolean userLogin(String email, String password);
    Boolean existsByUsername(String user);
    Boolean existedByPassword(String pass);
    User findUserByUsername(String username);
    public User findUserByPassword(String password);
    User saveUser(User user);
    void deleteUser(Long id);
    User findUserById(Long id);
    List<User> findAllUsers();
    void resetPassword(String email, String password);
    Boolean existedByEmail(String email);
    ResponseEntity<String> updateUser(Long id, String username, String email);
}
