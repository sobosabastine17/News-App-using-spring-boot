package com.example.OceanNews.Modules.User.Service;

import com.example.OceanNews.DTO.ResetPasswordDTO;
import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Modules.PaymentMode.PaymentMode;
import com.example.OceanNews.Modules.Roles.Roles;
import com.example.OceanNews.Modules.User.User;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findByUsername(String username);
    //AuthenticationResponse userLogin(AuthenticationRequest request) throws ELException;
    String userLogin(User credentials) throws ELException;
    Boolean existsByUsername(String user);
    Boolean existedByPassword(String pass);
    User findUserByUsername(String username);
    Optional<User> findUserByPassword(String password);
//    AuthenticationResponse saveUser(RegistrationRequest request) throws ELException;
    void deleteUser(Long id) throws ELException;
    User findUserById(Long id) throws ELException;
    List<User> findAllUsers() throws ELException;
    void resetPassword(Long id, ResetPasswordDTO pass)throws ELException;
    Boolean existedByEmail(String email) throws ELException;
   void update(Long id,User user)throws ELException;
   void changePassword(Long id,String password)throws ELException;
   void changeStatus(Long id,Long status)throws ELException;
   void changeRole(Long id, Roles role)throws ELException;
   List<User> findByRoles(Roles roles) throws ELException;
   void updateProfile(Long id,User user)throws ELException;
   void updateAvatar(Long id,String avatar)throws ELException;
    void updatePayment(Long id, PaymentMode paymentCredetials)throws ELException;
    void sendVerificationEmail(User user, String verificationLink) throws ELException;
    //AuthenticationResponse register(RegistrationRequest request) throws ELException;
   User userRegistration(User data) throws ELException, MessagingException;
   User findByToken(String token) throws ELException;
   void sendConfirmationEmail(User user,String token) throws ELException, MessagingException;
}
