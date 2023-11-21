package com.example.OceanNews.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Long categoryId;
    @Column
    private String subCategoryName,subCategoryDetails, createdBy;
    @Column
    private LocalDate createdDate;

}
