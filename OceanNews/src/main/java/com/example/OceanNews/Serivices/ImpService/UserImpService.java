package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Authentication.Config.JwtService;
import com.example.OceanNews.DTO.Auth.AuthenticationRequest;
import com.example.OceanNews.DTO.Auth.AuthenticationResponse;
import com.example.OceanNews.DTO.Auth.RegistrationRequest;
import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.Payment_Mode;
import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import com.example.OceanNews.Procedure.Utilities;
import com.example.OceanNews.Repo.UserRepo;
import com.example.OceanNews.Serivices.UserService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class UserImpService implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    @Override
    public AuthenticationResponse saveUser(RegistrationRequest request) throws ELException{
    try {
        //check if email is valid
        if (Utilities.isValidEmail(request.getEmail())) {
            throw new ELException("Email is not valid");
        }
        //check if email is existed
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ELException("Email is existed");
        }
        //check if username is existed
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new ELException("Username is existed");
        }
        //check if password is empty
        if (request.getPassword().isEmpty()) {
            throw new ELException("Password is empty");
        }
        //checking if phone number is valid
        if (Utilities.isValidPhoneNumber(request.getPhone())) {
            throw new ELException("Phone number is not valid");
        }
        //checking if password is valid
        if (!Utilities.isValidPassword(request.getPassword())) {
            throw new ELException("Password is not valid");
        }
        var ps = request.getPassword();
        String hashedPassword= Utilities.hashPassword(ps);
        request.setPassword(hashedPassword);
        //return userRepo.save(user);
        var user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                //.status(request.getStatus())
                .avatar(request.getAvatar())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .gender(request.getGender())
                .paymentMode(Payment_Mode.BANK_ACCOUNT)
                .payment_details(request.getPayment_details())
                .dob(request.getDob())
                .created_at(request.getCreated_at())
                .roles(Role.USER)
                .build();
        userRepo.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();

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
    public AuthenticationResponse userLogin(AuthenticationRequest request) throws ELException {
        if (request.getEmail()==null || request.getEmail().isEmpty()){
            throw new ELException("Username cannot be empty");
        }else {
                if (request.getPassword()==null || request.getPassword().isEmpty()){
                    throw new ELException("Password is empty");
                }else{
                    if (existedByEmail(request.getEmail())||existsByUsername(request.getEmail())){
                        String hashedPassword= Utilities.hashPassword(request.getPassword());

                        // Passwords match, authentication successful
                        User userEmail = userRepo.findByEmail(request.getEmail());
                        if (userEmail != null) {
                            User userPassword=userRepo.findByPassword2(hashedPassword);
                             if (userRepo.existsByPassword(hashedPassword)){
                                 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                         request.getEmail(),
                                         // Utilities.hashPassword(request.getPassword())
                                         //  hashedPassword
                                         request.getPassword()
                                 ));
//                            var user = userRepo.findByEmail(request.getEmail());
//                            return AuthenticationResponse.builder()
//                                    .token(jwtService.generateToken(user))
//                                    .build();
                                 var user = userRepo.findByEmail(request.getEmail());
                                 return AuthenticationResponse.builder()
                                         .token(jwtService.generateToken(user))
                                         .build();
                             }else {
                                 throw new ELException("Username or Password is not correct");
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
    public Boolean existsByUsername(String user) {
        return userRepo.existsByUsername(user);
    }
    @Override
    public Boolean existedByPassword(String pass) {
        return userRepo.existsByPassword(pass);
    }

    @Transactional
    @Override
    public void update(Long id,User user) throws ELException {
        User user1 = userRepo.findById(id).orElse(null);
        //checking if the fields are empty
       if (user.getFirstName().isEmpty() ||
                user.getLastName().isEmpty()||
               user.getEmail().isEmpty()||
               user.getPhone().isEmpty() ||
               user.getAddress().isEmpty()||
               user.getGender().isEmpty()||
               user.getPaymentMode().toString().isEmpty()||
               user.getPayment_details().isEmpty()||
               user.getRoles().toString().isEmpty()){
            throw new ELException("All fields are required");
       }
//          //checking if user is existed
        if (userRepo.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
            // checking if email is valid or not valid email format
            if (Utilities.isValidEmail(user.getEmail())) {
                throw new ELException("Email is not valid");
            }
            // checking if email is existed
            if (userRepo.existsByEmail(user.getEmail())) {
                throw new ELException("Email is existed");
            }
            // checking if username is existed
            if (userRepo.existsByUsername(user.getUsername())) {
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
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setGender(user.getGender());
            user1.setPaymentMode(user.getPaymentMode());
            user1.setPayment_details(user.getPayment_details());
            user1.setPhone(user.getPhone());
            user1.setRoles(user.getRoles());
            userRepo.save(user1);
//        }
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
        assert user != null;
        //checking if the fields are empty
        if (user.getStatus().toString().isEmpty()){
            throw new ELException("Status is required");
        }
        //checking if user is existed
        if (userRepo.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
           // user.setStatus(status);
            userRepo.save(user);
        }

    @Override
    public void changeRole(Long id, Role role) throws ELException {
        User user = userRepo.findById(id).orElse(null);
        assert user != null;
        //checking if the fields are empty
        if (user.getRoles().toString().isEmpty()){
            throw new ELException("Role is required");
        }
        //checking if user is existed
        if (userRepo.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
            user.setRoles(role);
            userRepo.save(user);
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
    public void updateProfile(Long id, User user) throws ELException {
        User user1 = userRepo.findById(id).orElse(null);
        if (userRepo.findUserById(id).isPresent()){
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
            if (userRepo.existsByEmail(user.getEmail())) {
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
            userRepo.save(user1);
        }
        throw new ELException("User not found");
    }

    @Override
    public void updateAvatar(Long id, String avatar) throws ELException {
        User user = userRepo.findById(id).orElse(null);
        //checking if the fields are empty
        assert user != null;
        if (user.getAvatar().isEmpty()){
            throw new ELException("Avatar is required");
        }
        //checking if user is existed
        if (userRepo.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
        user.setAvatar(avatar);
            userRepo.save(user);

        throw new ELException("User not found");
    }

    @Override
    public void updatePayment(Long id, Payment_Mode paymentMode, String paymentDetails) throws ELException {
        User user = userRepo.findById(id).orElse(null);
        assert user != null;
        //checking if the fields are empty
        if (user.getPayment_details().isEmpty() ||
                user.getPaymentMode().toString().isEmpty()){
            throw new ELException("All fields are required");
        }
        //checking if user is existed
        if (userRepo.findById(id).isEmpty()){
            throw new ELException("User not found");
        }
            user.setPaymentMode(paymentMode);
            user.setPayment_details(paymentDetails);
            userRepo.save(user);
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
    public AuthenticationResponse register(RegistrationRequest request) throws ELException {
        var user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                //.status(request.getStatus())
                .avatar(request.getAvatar())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .gender(request.getGender())
                .paymentMode(Payment_Mode.BANK_ACCOUNT)
                .payment_details(request.getPayment_details())
                .dob(request.getDob())
                .created_at(request.getCreated_at())
                .roles(Role.USER)
                .build();
        userRepo.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .username(request.getEmail())
                .role(request.getRole())
                .build();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findAllByUsername(username);
    }

    @Override
    public Optional<User> findUserByPassword(String password) {
        return userRepo.findByPassword(Utilities.hashPassword(password));
    }
}
