package com.example.OceanNews.Serivices;

import com.example.OceanNews.DTO.Auth.AuthenticationRequest;
import com.example.OceanNews.DTO.Auth.AuthenticationResponse;
import com.example.OceanNews.DTO.Auth.RegistrationRequest;
import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.Payment_Mode;
import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findByUsername(String username);
    AuthenticationResponse userLogin(AuthenticationRequest request) throws ELException;
    Boolean existsByUsername(String user);
    Boolean existedByPassword(String pass);
    User findUserByUsername(String username);
    public Optional<User> findUserByPassword(String password);
    AuthenticationResponse saveUser(RegistrationRequest request) throws ELException;
    void deleteUser(Long id) throws ELException;
    User findUserById(Long id) throws ELException;
    List<User> findAllUsers() throws ELException;
    void resetPassword(Long id, String email, String password)throws ELException;
    Boolean existedByEmail(String email) throws ELException;
   void update(Long id,User user)throws ELException;
   void changePassword(Long id,String password)throws ELException;
   void changeStatus(Long id,Long status)throws ELException;
   void changeRole(Long id,Role role)throws ELException;
   List<User> findByRoles(Role roles) throws ELException;
   void updateProfile(Long id,User user)throws ELException;
   void updateAvatar(Long id,String avatar)throws ELException;
    void updatePayment(Long id, Payment_Mode paymentMode,String paymentDetails)throws ELException;
    void sendVerificationEmail(User user, String verificationLink) throws ELException;
    AuthenticationResponse register(RegistrationRequest request) throws ELException;

}
