package com.example.OceanNews.Modules.User.ServiceImplementation;

import com.example.OceanNews.DTO.ResetPasswordDTO;
import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Modules.PaymentMode.PaymentMode;
import com.example.OceanNews.Modules.Roles.Roles;
import com.example.OceanNews.Modules.User.User;
import com.example.OceanNews.Procedure.EmailService;
import com.example.OceanNews.Procedure.Utilities;
import com.example.OceanNews.Modules.User.Repository.UserRepository;
import com.example.OceanNews.Modules.User.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class UserImplementationService implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public String userLogin(User credentials) throws ELException {
        User userEmail = userRepository.findByEmail(credentials.getEmail());
        if (credentials.getEmail() == null || credentials.getEmail().isEmpty()){
            throw new ELException("Username cannot be empty");
        }else {
            if (credentials.getPassword()==null || credentials.getPassword().isEmpty()){
                throw new ELException("Password is empty");
            }else{
                if (existedByEmail(credentials.getEmail())||existsByUsername(credentials.getUsername())){
                    String hashedPassword= Utilities.hashPassword(credentials.getPassword());
                    // Passwords match, authentication successful

                    if (userEmail != null) {
                        if (userEmail.getPassword().equals(hashedPassword)) {
                          return "Login successful";
                        }else {
                            throw new ELException("Username or Password is not correct1");
                        }

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
    public void deleteUser(Long id) {
          userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void resetPassword(Long id, ResetPasswordDTO newPass) throws ELException {
      //  if (userRepo.findById(id).isPresent()){
         // User user1 = userRepo.findByEmail(user.getEmail());
            User user = userRepository.findById(id).orElse(null);
            //find user if user is existed or not existed in the database
          if (user==null){
              throw new ELException("Account not found");
          }
            if (newPass.getPassword().isEmpty() || newPass.getConfirmPassword().isEmpty()){
                throw new ELException("Password is required");
            }
            if (!Utilities.isValidPassword(newPass.getPassword())){
                throw new ELException("Password is not valid");
            }
            if (!newPass.getPassword().equals(newPass.getConfirmPassword())){
                throw new ELException("Password does not match");
            }
            //check if password is existed
            String dbPassword = user.getPassword();
            if (Utilities.hashPassword(newPass.getPassword()).equals(dbPassword)){
                throw new ELException("You can not used the same password");
            }
          user.setPassword(Utilities.hashPassword(newPass.getPassword()));
          userRepository.save(user);
    //}throw new ELException("User not found");
     }

    @Override
    public Boolean existedByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String user) {
        return userRepository.existsByUsername(user);
    }
    @Override
    public Boolean existedByPassword(String pass) {
        String hashedPassword= Utilities.hashPassword(pass);
        return userRepository.existsByPassword(hashedPassword);
    }

    @Transactional
    @Override
    public void update(Long id,User user) throws ELException {
        User user1 = userRepository.findById(id).orElse(null);
        //checking if the fields are empty
       if (user.getFirstName().isEmpty() ||
               user.getLastName().isEmpty()||
               user.getEmail().isEmpty()||
               user.getPhone().isEmpty() ||
               user.getAddress().isEmpty()||
               user.getGender().isEmpty()||
               user.getPaymentMode().toString().isEmpty()||
               user.getPaymentMode().getPaymentMode().isEmpty()||
               user.getRoles().toString().isEmpty()){
            throw new ELException("All fields are required");
       }
//          //checking if user is existed
        if (userRepository.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
            // checking if email is valid or not valid email format
            if (Utilities.isValidEmail(user.getEmail())) {
                throw new ELException("Email is not valid");
            }
            // checking if email is existed
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new ELException("Email is existed");
            }
            // checking if username is existed
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new ELException("Username is existed");
            }
            // checking if phone number is valid
            if (Utilities.isValidPhoneNumber(user.getPhone())) {
                throw new ELException("Phone number is not valid");
            }
        assert user1 != null;
        user1.setAddress(user.getAddress());
            user1.setAvatar(user.getAvatar());
            user1.setDob(user.getDob());
            user1.setEmail(user.getEmail());
            user1.setFirstName(user.getFirstName().toUpperCase());
            user1.setLastName(user.getLastName().toUpperCase());
            user1.setGender(user.getGender());
            user1.setPaymentMode(user.getPaymentMode());
            user1.setPhone(user.getPhone());
            user1.setRoles(user.getRoles());
            userRepository.save(user1);
//        }
    }

    @Override
    public void changePassword(Long id, String password) throws ELException {
        User user = userRepository.findById(id).orElse(null);
        if (user!=null){
            user.setPassword(Utilities.hashPassword(password));
            userRepository.save(user);
        }
        throw new ELException("User not found");
    }

    @Override
    public void changeStatus(Long id, Long status) throws ELException {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        //checking if the fields are empty
        if (user.getStatus().toString().isEmpty()){
            throw new ELException("Status is required");
        }
        //checking if user is existed
        if (userRepository.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
           // user.setStatus(status);
            userRepository.save(user);
        }

    @Override
    public void changeRole(Long id, Roles role) throws ELException {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        //checking if the fields are empty
        if (user.getRoles().toString().isEmpty()){
            throw new ELException("Role is required");
        }
        //checking if user is existed
        if (userRepository.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
            user.setRoles(user.getRoles());
            userRepository.save(user);
        }

    @Override
    public List<User> findByRoles(Roles roles) throws ELException {
        if (userRepository.findByRoles(roles.getId()).isEmpty()){
            throw new ELException("Role does not exist or is empty");
        }
        return userRepository.findByRoles(roles.getId()) ;
    }

    @Override
    public void updateProfile(Long id, User user) throws ELException {
        User user1 = userRepository.findById(id).orElse(null);
        if (userRepository.findUserById(id).isPresent()){
            //checking if the fields are empty
            if (user.getFirstName().isEmpty() ||
                    user.getLastName().isEmpty()||
                    user.getEmail().isEmpty()||
                    user.getPhone().isEmpty()){
                throw new ELException("All fields are required");
            }
            // checking if email is valid or not valid email format
            if (Utilities.isValidEmail(user.getEmail())) {
                throw new ELException("Email is not valid");
            }
            // checking if email is existed
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new ELException("Email is existed");
            }
            //checking if phone number is valid
            if (Utilities.isValidPhoneNumber(user.getPhone())) {
                throw new ELException("Phone number is not valid");
            }
            assert user1 != null;
            user1.setAvatar(user.getAvatar());
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());
            userRepository.save(user1);
        }
        throw new ELException("User not found");
    }

    @Override
    public void updateAvatar(Long id, String avatar) throws ELException {
        User user = userRepository.findById(id).orElse(null);
        //checking if the fields are empty
        assert user != null;
        if (user.getAvatar().isEmpty()){
            throw new ELException("Avatar is required");
        }
        //checking if user is existed
        if (userRepository.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
        user.setAvatar(avatar);
            userRepository.save(user);

        throw new ELException("User not found");
    }

    @Override
    public void updatePayment(Long id, PaymentMode paymentCredetials) throws ELException {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        //checking if the fields are empty
        if (user.getPaymentMode().getPaymentMode().isEmpty() ||
                user.getPaymentMode().getPaymentMode().isEmpty()){
            throw new ELException("All fields are required");
        }
        //checking if user is existed
        if (userRepository.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
          //  user.setPaymentMode(paymentMode);
        user.setPaymentMode(paymentCredetials);
            userRepository.save(user);
        }

    @Override
    public void sendVerificationEmail(User user, String Link) throws ELException {
        SimpleMailMessage msg = new SimpleMailMessage();
        // check if email is valid
        if (Utilities.isValidEmail(user.getEmail())) {
            throw new ELException("Email is not valid");
        }
        msg.setTo(user.getEmail());
        msg.setSubject("Email Verification");
         Link = "localhost:8080/api/newsApp/v1/user/verify?token=" + Utilities.generateToken(); // Create verification link
        msg.setText("Click the link to verify your email: " + Link);
//        javaMailSender.send(msg);
    }

    @Override
    public User userRegistration(User data) throws ELException, MessagingException {
        //check if email is valid
        if (Utilities.isValidEmail(data.getEmail())) {
            throw new ELException("Email is not valid");
        }
        //check if email is existed
        if (userRepository.existsByEmail(data.getEmail())) {
            throw new ELException(String.valueOf(ResponseEntity.status(400).body("Email is existed")));
        }
        //check if username is existed
        if (userRepository.existsByUsername(data.getUsername())) {
            throw new ELException("Username is existed");
        }
        //check if password is empty
        if (data.getPassword().isEmpty()) {
            throw new ELException("Password is empty");
        }
        //checking if phone number is valid
        if (Utilities.isValidPhoneNumber(data.getPhone())) {
            throw new ELException("Phone number is not valid");
        }
        //checking if password is valid
        if (!Utilities.isValidPassword(data.getPassword())) {
            throw new ELException("Password is not valid");
        }
        var ps = data.getPassword();
        String hashedPassword= Utilities.hashPassword(ps);
        data.setPassword(hashedPassword);
        String token = Utilities.generateToken();
                     data.setRoles(data.getRoles());
                     data.setPassword(data.getPassword());
                     data.setAvatar(data.getAvatar());
                     data.setAddress(data.getAddress());
                     data.setDob(data.getDob());
                     data.setEmail(data.getEmail());
                     data.setFirstName(data.getFirstName().toUpperCase());
                     data.setLastName(data.getLastName().toUpperCase());
                     data.setGender(data.getGender());
                     data.setPaymentMode(data.getPaymentMode());
                     data.setPhone(data.getPhone());
                     data.setRoles(data.getRoles());
                     data.setToken(token);
                     userRepository.save(data);
                     emailService.sendConfirmationEmail(data.getEmail(), token);
                     return data;
    }

    @Override
    public User findByToken(String token) throws ELException {
        Optional<User> optionalUser = userRepository.findByToken(token);
        if (optionalUser .isPresent()) {
            User user = optionalUser.get();
            user.setEmailVerified(true);
            userRepository.save(user);
        } else {
            throw new ELException("Invalid token. Please try again.");
        }
        return optionalUser.get();
    }

    @Override
    public void sendConfirmationEmail(User user,String token) throws ELException, MessagingException {
        String confirmationMai = emailService.sendConfirmationEmail(user.getEmail(),token);
        //checking if email is sent successfully
        if (confirmationMai == null) {
            throw new ELException("Email not sent");
        }
           user.setToken(token);
           userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }

    @Override
    public Optional<User> findUserByPassword(String password) {
        return userRepository.findByPassword(Utilities.hashPassword(password));
    }
}
