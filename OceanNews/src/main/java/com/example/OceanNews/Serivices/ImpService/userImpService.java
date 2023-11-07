package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.User;
import com.example.OceanNews.Procedure.Utilities;
import com.example.OceanNews.Repo.UserRepo;
import com.example.OceanNews.Serivices.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class userImpService implements UserService {

    @Autowired
    UserRepo userRepo;
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
          userRepo.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void resetPassword(String email, String password) {
          User user = userRepo.findByEmail(email);
          user.setPassword(password);
          userRepo.save(user);
    }

    @Override
    public Boolean existedByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Boolean userLogin(String email, String password) {
         userRepo.existsByEmail(email);
         userRepo.existsByPassword(password);
         return true;
    }

    @Override
    public Boolean existsByUsername(String user) {
        return userRepo.existsByUsername(user);
    }

    @Override
    public Boolean existedByPassword(String pass) {
        return userRepo.existsByPassword(pass);
    }

    @Transactional
    @Override
    public ResponseEntity<String> updateUser(Long id,
                                             String username,
                                             String email) {
        User user= userRepo.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "user with ID "+id+" does not exist"
                ));
        if (username!=null && !username.isEmpty() && !Objects.equals(user.getUsername(),username)){
            Optional<User> userOptional= Optional.ofNullable(userRepo.findAllByUsername(username));
            if (userOptional.isPresent()){
                throw new IllegalStateException("Username taken");
            }
            user.setUsername(username);
        }else {
            throw new IllegalStateException("username is exist");
        }
        if (email!=null &&
                !email.isEmpty() &&
                !Objects.equals(user.getUsername(),email)){
                if (!Utilities.isValidEmail(email)){
                    throw new IllegalStateException("Email is not valid");
                }
            Optional<User> userOptional= Optional.ofNullable(userRepo.findByEmail(email));
            if (userOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            user.setEmail(email);
        }
        return ResponseEntity.ok("User updated Successful");
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findAllByUsername(username);
    }
}
