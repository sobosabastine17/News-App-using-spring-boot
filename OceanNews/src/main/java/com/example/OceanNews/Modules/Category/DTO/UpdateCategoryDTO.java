package com.example.OceanNews.Modules.Category.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCategoryDTO {
    private Long id;
    private String categoryName,categoryDetails;
}
