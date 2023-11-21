package com.example.OceanNews.Serivices;

import com.example.OceanNews.Model.SubCategory;
import org.springframework.stereotype.Service;

@Service
public interface SubCategoryService {
    SubCategory addSubCategory(SubCategory subCategory);
    void editSubCategory(Long id);
    void deleteSubCategory(long id);
    Iterable<SubCategory> allSubCategory();
}
