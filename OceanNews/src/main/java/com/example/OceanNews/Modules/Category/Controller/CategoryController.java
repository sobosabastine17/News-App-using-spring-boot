package com.example.OceanNews.Modules.Category.Controller;

import com.example.OceanNews.Modules.Category.DTO.UpdateCategoryDTO;
import com.example.OceanNews.Modules.Category.Category;
import com.example.OceanNews.Modules.Category.ServiceImplementation.CategoryImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsApp/v1")
public class CategoryController {
    @Autowired
    private CategoryImplementationService categoryImplementationService;
    @PostMapping("category/add")
    ResponseEntity<String> postCategory(@RequestBody Category category){
        if (categoryImplementationService.checkCategoryNameExist(category.getCategoryName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category name exist");
        }
       if(categoryImplementationService.addCategory(category)!=null){
           return   ResponseEntity.ok("Category created");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category could not be sent");
    }
 @GetMapping("/category/get")
 Iterable<Category> allCat(){
       return categoryImplementationService.allCategory();
 }
    @DeleteMapping("/category/delete/{id}")
        ResponseEntity<String> deleteCategory(@PathVariable Long id){
            if (categoryImplementationService.deleteCategoty(id)!=null){
                return ResponseEntity.ok("Category name deleted");
            }
            return ResponseEntity.ok("not able to delete");
    }
    @GetMapping("/category/type/{id}")
    public ResponseEntity<String> getCategoryNameById(@PathVariable Long id) {
        String categoryName = categoryImplementationService.categoryNameById(id);
        if (categoryName != null) {
            return ResponseEntity.ok(categoryName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/category/update/{id}")
    ResponseEntity<String> updateCategory(@PathVariable Long id,@RequestBody UpdateCategoryDTO updateCategory){
        if (categoryImplementationService.checkCategoryNameExist(updateCategory.getCategoryName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category name exist");
        }
        if(categoryImplementationService.updateCategory(updateCategory)!=null){
            return   ResponseEntity.ok("Category updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category could not be sent");
    }
}
