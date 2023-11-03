package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Category;
import com.example.OceanNews.Repo.CategoryRepo;
import com.example.OceanNews.Serivices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpService implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void deleteCategoty(Long id) {

    }

    @Override
    public boolean checkCategoryNameExist(String name) {
      return  categoryRepo.existsByCategoryName(name);
    }

    @Override
    public Iterable<Category> allCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Iterable<Category> categoryType(Category category) {
        return null;
    }
}
