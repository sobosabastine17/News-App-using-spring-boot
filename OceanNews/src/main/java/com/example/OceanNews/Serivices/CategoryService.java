package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category addCategory(Category category);
    String deleteCategoty(Long id);
    boolean checkCategoryNameExist(String name);
    Iterable<Category> allCategory();
    String categoryNameById(Long ig);
}
