package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Role;
import com.example.OceanNews.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);

    @Query("select (count(u) > 0) from User u where u.password = ?1")
    boolean existsByPassword(String password);
    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean existsByUsername(String username);
@Query("select u from User u where u.roles = ?1")
List <User> findByRoles(Role roles);
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    User findAllByUsername(String username);

    @Query("select u from User u where u.password = ?1")
    User findByPassword(String password);
}
