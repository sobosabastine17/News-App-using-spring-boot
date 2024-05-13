package com.example.OceanNews.Modules.Category.Repository;

import com.example.OceanNews.Modules.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Transactional
    @Modifying
    @Query("update Category c set c.id = ?1 where c.id = ?2")
    int updateById(Long id);
    @Query("select c from Category c where c.categoryName = ?1")
    List<Category> findByCategoryName(Long categoryName);
    @Query("select (count(c) > 0) from Category c where c.categoryName = ?1")
    boolean existsByCategoryName(String categoryName);

    @Override
    boolean existsById(Long aLong);
}
