package com.example.OceanNews.Modules.SubCategory.ServiceImplementation;

import com.example.OceanNews.Modules.SubCategory.SubCategory;
import com.example.OceanNews.Modules.SubCategory.Repository.SubCategoryRepository;
import com.example.OceanNews.Modules.SubCategory.Service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryImpService implements SubCategoryService {
    private SubCategoryRepository subCategoryRepository;
    @Override
    public SubCategory addSubCategory(SubCategory subCategory) {
      return subCategoryRepository.save(subCategory);
    }
    @Override
    public void editSubCategory(Long id) {
        Optional<SubCategory> categoryID= subCategoryRepository.findById(id);
        if (categoryID.isPresent()){
        }
    }

    @Override
    public void deleteSubCategory(long id) {

    }

    @Override
    public Iterable<SubCategory> allSubCategory() {
        return null;
    }
}
