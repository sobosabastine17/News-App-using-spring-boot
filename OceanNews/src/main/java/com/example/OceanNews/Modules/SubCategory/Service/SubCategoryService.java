package com.example.OceanNews.Modules.SubCategory.Service;

import com.example.OceanNews.Modules.SubCategory.SubCategory;
import org.springframework.stereotype.Service;

@Service
public interface SubCategoryService {
    SubCategory addSubCategory(SubCategory subCategory);
    void editSubCategory(Long id);
    void deleteSubCategory(long id);
    Iterable<SubCategory> allSubCategory();
}
