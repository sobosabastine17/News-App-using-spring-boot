package com.example.OceanNews.Serivices;

import com.example.OceanNews.Exception.ELException;
import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findByUsername(String username);
    Boolean userLogin(String username,String password) throws ELException;
    Boolean existsByUsername(String user);
    Boolean existedByPassword(String pass);
    User findUserByUsername(String username);
    public User findUserByPassword(String password);
    User saveUser(User user) throws ELException;
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

    void updateUser(Long id, String username, String email);
}
