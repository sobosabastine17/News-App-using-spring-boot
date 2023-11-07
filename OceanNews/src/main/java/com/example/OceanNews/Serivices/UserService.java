package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User saveUser(User user);
    List<User> findByUsername(String username);
    void deleteUser(Long id);
    User findUserById(Long id);
    List<User> findAllUsers();
    void resetPassword(String email, String password);
    Boolean existedByEmail(String email);
    Boolean userLogin(String email, String password);
    Boolean existsByUsername(String user);
    Boolean existedByPassword(String pass);
    ResponseEntity<String> updateUser(Long id, String username, String email);

    User findUserByUsername(String username);

    User findUserByPassword(String password);
}
