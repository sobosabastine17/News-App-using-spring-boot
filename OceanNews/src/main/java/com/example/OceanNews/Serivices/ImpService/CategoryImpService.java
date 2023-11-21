package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.DTO.Category.CreateCategoryDTO;
import com.example.OceanNews.DTO.Category.GetCategoryResponsesDTO;
import com.example.OceanNews.DTO.Category.UpdateCategoryDTO;
import com.example.OceanNews.Model.Category;
import com.example.OceanNews.Repo.CategoryRepo;
import com.example.OceanNews.Serivices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpService implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public GetCategoryResponsesDTO addCategory(CreateCategoryDTO categoryDTO) {
        var category = new Category();
        category.setCategoryName(category.getCategoryName());
        category.setCategoryDetails(category.getCategoryDetails());
        category.setCreatedBy(category.getCreatedBy());
        var catSave  = categoryRepo.save(category);
         var categoryReturns= new GetCategoryResponsesDTO();
         categoryReturns.setId(catSave.getId());
         categoryReturns.setCategoryName(catSave.getCategoryName());
         categoryReturns.setCategoryDetails(catSave.getCategoryDetails());
         return categoryReturns;


    }

    @Override
    public GetCategoryResponsesDTO updateCategory(UpdateCategoryDTO updateCategoryDTO) {
        var category= new Category();

        var existingCategory=categoryRepo.findById(updateCategoryDTO.getId());

        // get category from db using id
        // check if category is does not exist
        if (existingCategory.isEmpty()){
            return  null;
        }

        category.setId(existingCategory.get().getId());
        category.setCategoryName(existingCategory.get().getCategoryName());
        category.setCategoryDetails(existingCategory.get().getCategoryDetails());
        category.setCreatedBy(existingCategory.get().getCreatedBy());
        category.setDate(existingCategory.get().getDate());

        // persist changes
        var saved = categoryRepo.save(category);

        var response = new GetCategoryResponsesDTO();
        response.setId(saved.getId());
        response.setCategoryName(saved.getCategoryName());
        response.setCategoryDetails(saved.getCategoryDetails());

        return response;
    }

    @Override
    public String deleteCategoty(Long id) {
        try {
//            Optional<Category> category=categoryRepo.findById(id);
            boolean existCat=categoryRepo.existsById(id);
            categoryRepo.deleteById(id);
        }catch (IllegalStateException ex){
            return "Category with id "+id+" does not exist";
        }
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
