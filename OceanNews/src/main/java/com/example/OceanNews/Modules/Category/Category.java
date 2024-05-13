package com.example.OceanNews.Modules.Category;

import com.example.OceanNews.Model.Model;
import com.example.OceanNews.Modules.SubCategory.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Category extends Model {
    @Column
    private String categoryName;
    @Column
    private String categoryDetails;
    @ManyToOne
    private SubCategory subCategory;

}
