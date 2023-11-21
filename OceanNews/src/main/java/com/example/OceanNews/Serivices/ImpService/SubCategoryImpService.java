package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.SubCategory;
import com.example.OceanNews.Repo.SubCategoryRepo;
import com.example.OceanNews.Serivices.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryImpService implements SubCategoryService {
    private SubCategoryRepo subCategoryRepo;
    @Override
    public SubCategory addSubCategory(SubCategory subCategory) {
      return subCategoryRepo.save(subCategory);
    }
    @Override
    public void editSubCategory(Long id) {
        Optional<SubCategory> categoryID=subCategoryRepo.findById(id);
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
