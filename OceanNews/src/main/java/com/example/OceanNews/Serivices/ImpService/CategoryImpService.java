package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Category;
import com.example.OceanNews.Repo.CategoryRepo;
import com.example.OceanNews.Serivices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String deleteCategoty(Long id) {
        boolean existCat=categoryRepo.existsById(id);
        Category category=categoryRepo.findById(id).orElseThrow(
                ()-> new IllegalStateException("ID not "+id+" not found")
        );
       // if (categoryRepo.existsById(id)){
            categoryRepo.deleteById(id);
       // }
        return "Category with id "+id+" does not exist";
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
    public String categoryNameById(Long id) {
         Category category = categoryRepo.findById(id).orElse(null);
            if (category!=null){
                return category.getCategoryName();
            }
        return "Category with id "+id+" does not exist";
    }
}
