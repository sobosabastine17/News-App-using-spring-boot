package com.example.OceanNews.Serivices;

import com.example.OceanNews.DTO.Category.GetCategoryResponsesDTO;
import com.example.OceanNews.DTO.Category.UpdateCategoryDTO;
import com.example.OceanNews.Model.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category addCategory(Category category);
    //GetCategoryResponsesDTO updateCategory(Long id);
    GetCategoryResponsesDTO updateCategory(UpdateCategoryDTO updateCategoryDTO);
    String deleteCategoty(Long id);
    boolean checkCategoryNameExist(String name);
    Iterable<Category> allCategory();
    String categoryNameById(Long ig);
}
