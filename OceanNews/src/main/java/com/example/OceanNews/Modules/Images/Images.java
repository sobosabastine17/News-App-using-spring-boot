package com.example.OceanNews.Modules.Images;

import com.example.OceanNews.Model.Model;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Images extends Model {
    private String name;
    private String filePath;
    private Long status;
}
