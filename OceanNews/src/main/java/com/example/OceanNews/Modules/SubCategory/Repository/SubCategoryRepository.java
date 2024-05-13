package com.example.OceanNews.Modules.SubCategory.Repository;

import com.example.OceanNews.Modules.SubCategory.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {

    @Override
    Optional<SubCategory> findById(Long aLong);
}
