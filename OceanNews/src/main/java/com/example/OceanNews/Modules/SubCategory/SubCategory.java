package com.example.OceanNews.Modules.SubCategory;

import com.example.OceanNews.Model.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubCategory extends Model {
    @Column
    private String subCategoryName,subCategoryDetails;

}
