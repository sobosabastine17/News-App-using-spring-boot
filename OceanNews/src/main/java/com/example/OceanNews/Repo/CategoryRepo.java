package com.example.OceanNews.Repo;

import com.example.OceanNews.Controllers.CategoryController;
import com.example.OceanNews.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.categoryName = ?1")
    List<Category> findByCategoryName(Long categoryName);
    @Query("select (count(c) > 0) from Category c where c.categoryName = ?1")
    boolean existsByCategoryName(String categoryName);
    @Override
    boolean existsById(Long aLong);
}
