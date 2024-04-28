package com.example.OceanNews.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Category extends Model{
    @Column
    private String categoryName;
    @Column
    private String categoryDetails;
    @ManyToOne
    private SubCategory subCategory;

}
