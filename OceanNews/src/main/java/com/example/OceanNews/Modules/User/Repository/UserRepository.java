package com.example.OceanNews.Modules.User.Repository;

import com.example.OceanNews.Modules.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);

    @Query("select (count(u) > 0) from User u where u.password = ?1")
    boolean existsByPassword(String password);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.password = :password")
    boolean existsByPassword2(@Param("password") String password);
    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean existsByUsername(String username);
@Query("select u from User u where u.id = ?1")
List <User> findByRoles(Long roles);
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    User findAllByUsername(String username);

    @Query("select u from User u where u.password = ?1")
    //boolean findByPassword(String password);
    Optional<User> findByPassword(String hashedPassword);

    @Query("select u from User u where u.password = ?1")
        User findByPassword2(String password);

    @Query("select u from User u where u.id = ?1")
    Optional<User> findUserById(Long id);
//    @Query("select u from User u where u.verificationToken = ?1")
//    User findByVerificationToken(String verificationToken);

    // This query verifies the password for the given email
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<Object> findEmailAndPassword(String email, String password);

    Optional<User> findByToken(String token);
    // this is for the authentication token
   // Optional<User> findByEmail(String email);
    //JPA Query to update the user's token
    @Query("UPDATE User u SET u.token = :token WHERE u.email = :email")
    void updateToken(String token, String email);
    //JPA Query to update the user's email verification status to true
    @Query("UPDATE User u SET u.emailVerified = true WHERE u.email = :email")
    void updateEmailVerificationStatus(String email);
}
