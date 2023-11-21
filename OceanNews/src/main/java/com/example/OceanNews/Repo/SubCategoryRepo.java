package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {

    @Override
    Optional<SubCategory> findById(Long aLong);
}
