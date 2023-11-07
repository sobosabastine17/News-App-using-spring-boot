package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPassword(String password);

    boolean existsByUsername(String username);


List <User> findByRole(String role);
    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);

    User findAllByUsername(String username);

    @Query("select u from User u where u.password = ?1")
    User findByPassword(String password);
}
