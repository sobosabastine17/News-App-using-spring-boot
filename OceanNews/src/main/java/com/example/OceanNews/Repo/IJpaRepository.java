package com.example.OceanNews.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaRepository<E,ID> extends JpaRepository<E,ID> {
}
