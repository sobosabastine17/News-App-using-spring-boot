package com.example.OceanNews.Modules.Category.Service;

import com.example.OceanNews.Modules.Category.DTO.GetCategoryResponsesDTO;
import com.example.OceanNews.Modules.Category.DTO.UpdateCategoryDTO;
import com.example.OceanNews.Modules.Category.Category;
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
