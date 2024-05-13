package com.example.OceanNews.Modules.Category.ServiceImplementation;

import com.example.OceanNews.Modules.Category.DTO.GetCategoryResponsesDTO;
import com.example.OceanNews.Modules.Category.DTO.UpdateCategoryDTO;
import com.example.OceanNews.Modules.Category.Category;
import com.example.OceanNews.Modules.Category.Repository.CategoryRepository;
import com.example.OceanNews.Modules.Category.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImplementationService implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
      //  var category = new Category();
        category.setCategoryName(category.getCategoryName());
        category.setCategoryDetails(category.getCategoryDetails());
        category.setSubCategory(category.getSubCategory());
        return categoryRepository.save(category);
     }

    @Override
    public GetCategoryResponsesDTO updateCategory(UpdateCategoryDTO updateCategoryDTO) {
        var category= new Category();

        var existingCategory= categoryRepository.findById(updateCategoryDTO.getId());

        // get category from db using id
        // check if category is doing not exist
        if (existingCategory.isEmpty()){
            return  null;
        }

        //category.setId(existingCategory.get().getId());
        category.setCategoryName(existingCategory.get().getCategoryName());
        category.setCategoryDetails(existingCategory.get().getCategoryDetails());

        // persist changes
        var saved = categoryRepository.save(category);

        var response = new GetCategoryResponsesDTO();
        //response.setId(saved.getId());
        response.setCategoryName(saved.getCategoryName());
        response.setCategoryDetails(saved.getCategoryDetails());

        return response;
    }

    @Override
    public String deleteCategoty(Long id) {
        try {
//            Optional<Category> category=categoryRepo.findById(id);
            boolean existCat= categoryRepository.existsById(id);
            categoryRepository.deleteById(id);
        }catch (IllegalStateException ex){
            return "Category with id "+id+" does not exist";
        }
        return "Category with id "+id+" does not exist";
    }

    @Override
    public boolean checkCategoryNameExist(String name) {
      return  categoryRepository.existsByCategoryName(name);
    }

    @Override
    public Iterable<Category> allCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public String categoryNameById(Long id) {
         Category category = categoryRepository.findById(id).orElse(null);
            if (category!=null){
                return category.getCategoryName();
            }
        return "Category with id "+id+" does not exist";
    }
}
