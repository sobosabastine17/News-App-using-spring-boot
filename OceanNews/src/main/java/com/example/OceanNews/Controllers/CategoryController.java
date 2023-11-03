package com.example.OceanNews.Controllers;

import com.example.OceanNews.Model.Category;
import com.example.OceanNews.Serivices.ImpService.CategoryImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private CategoryImpService categoryImpService;
    @PostMapping("category/add")
    ResponseEntity<String> postCategory(@RequestBody Category category){
        if (categoryImpService.checkCategoryNameExist(category.getCategoryName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category name exist");
        }
       if(categoryImpService.addCategory(category)!=null){
           return   ResponseEntity.ok("Category created");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category could not be sent");
    }
 @GetMapping("/category/get")
 Iterable<Category> allCat(){
       return categoryImpService.allCategory();
 }
}
