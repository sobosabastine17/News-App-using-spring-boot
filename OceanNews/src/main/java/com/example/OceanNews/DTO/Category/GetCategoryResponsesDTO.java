package com.example.OceanNews.DTO.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryResponsesDTO {
    private Long id;
    private String categoryName,createdBy,categoryDetails;
}
