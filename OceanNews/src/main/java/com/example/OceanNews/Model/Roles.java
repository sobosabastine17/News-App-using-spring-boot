package com.example.OceanNews.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Roles extends Model{
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

}
