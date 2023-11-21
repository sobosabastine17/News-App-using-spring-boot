package com.example.OceanNews.DTO.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCategoryDTO {
    private Long id;
    private String categoryName,categoryDetails;
}
