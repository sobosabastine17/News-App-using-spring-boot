package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import com.example.OceanNews.Procedure.Utilities;
import com.example.OceanNews.Repo.UserRepo;
import com.example.OceanNews.Serivices.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpService implements UserService {

    @Autowired
    UserRepo userRepo;
//    @Autowired
//     BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Override
    public User saveUser(User user) throws ELException{

try {
    try {
        //check if email is valid
        if (!Utilities.isValidEmail(user.getEmail())) {
            throw new ELException("Email is not valid");
        }
        //check if email is existed
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new ELException("Email is existed");
        }
        //check if username is existed
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new ELException("Username is existed");
        }
        //check if password is empty
        if (user.getPassword().isEmpty()) {
            throw new ELException("Password is empty");
        }
        //checking if phone number is valid
        if (!Utilities.isValidPhoneNumber(user.getPhone())) {
            throw new ELException("Phone number is not valid");
        }
        //checking if password is valid
        if (!Utilities.isValidPassword(user.getPassword())) {
            throw new ELException("Password is not valid");
        }
        var ps = user.getPassword();
        String hashedPassword= Utilities.hashPassword(ps);
        user.setPassword(hashedPassword);
        return userRepo.save(user);
    }catch (Exception e){
        throw new ELException(e.toString());
    }

}catch (Exception e){
    throw new ELException(e.toString());
}
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
    public void resetPassword(Long id, String email, String password) throws ELException {
        if (userRepo.findById(id).isPresent()){
          User user = userRepo.findByEmail(email);
          if (user==null){
              throw new ELException("Email not found");
          }
          user.setPassword(password);
          userRepo.save(user);
    }throw new ELException("User not found");
    }

    @Override
    public Boolean existedByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Boolean userLogin(String username,String password) throws ELException {
        if (username==null || username.isEmpty()){
            throw new ELException("Username cannot be empty");
        }else {
                if (password==null || password.isEmpty()){
                    throw new ELException("Password is empty");
                }else{
                    if (existedByEmail(username)||existsByUsername(username)){
                        String hashedPassword= Utilities.hashPassword(password);
                        // Passwords match, authentication successful
                        if (userRepo.findByPassword(hashedPassword)!=null){
                            // Passwords match, authentication successful
                            return true;
                        }else {
                            throw new ELException("Username or Password is not correct");
                        }
                }else {
                    throw new ELException("Username or Password does not exist");
                }
            }
        }
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
    public void update(Long id,User user) {
        User user1 = userRepo.findById(id).orElse(null);
        if (user1!=null){
            user1.setAddress(user.getAddress());
            user1.setAvatar(user.getAvatar());
            user1.setDob(user.getDob());
            user1.setEmail(user.getEmail());
            user1.setFullname(user.getFullname());
            user1.setGender(user.getGender());
            user1.setPaymentMode(user.getPaymentMode());
            user1.setPayment_details(user.getPayment_details());
            user1.setPhone(user.getPhone());
            user1.setRoles(user.getRoles());
            userRepo.save(user1);
        }
        throw new ELException("User not found");
    }

    @Override
    public void changePassword(Long id, String password) throws ELException {
        User user = userRepo.findById(id).orElse(null);
        if (user!=null){
            user.setPassword(Utilities.hashPassword(password));
            userRepo.save(user);
        }
        throw new ELException("User not found");
    }

    @Override
    public void changeStatus(Long id, Long status) throws ELException {
        User user = userRepo.findById(id).orElse(null);
        if (user!=null){
            user.setStatus(status);
            userRepo.save(user);
        }
        throw new ELException("User not found");
    }

    @Override
    public void changeRole(Long id, Role role) throws ELException {
        User user = userRepo.findById(id).orElse(null);
        if (user!=null){
            user.setRoles(role);
            userRepo.save(user);
        }
        throw new ELException("User not found");
    }

    @Override
    public List<User> findByRoles(Role roles) throws ELException {
        //check if role is existed or is empty list
        //User findAllUserByRole = userRepo.
        if (userRepo.findByRoles(roles).isEmpty()){
            throw new ELException("Role does not exist or is empty");
        }
        return userRepo.findByRoles(roles) ;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findAllByUsername(username);
    }

    @Override
    public User findUserByPassword(String password) {
        return userRepo.findByPassword(password);
    }
}
