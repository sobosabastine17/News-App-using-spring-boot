package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
    @Query("select r from Roles r where r.name = ?1")
    Roles findByName(String name);

    @Query("select r from Roles r where r.name = ?1")
    Roles findRolesByName(String name);
}
