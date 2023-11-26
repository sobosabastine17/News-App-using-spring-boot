package com.example.OceanNews.Repo;

import com.example.OceanNews.DTO.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IJpaRepository <E extends BaseEntity,ID extends Serializable>
        extends JpaRepository <E,ID> {
    @Query("select p from Post p where p.id = ?1")
    E findByPostID(ID id);
}
