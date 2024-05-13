package com.example.OceanNews.Modules.Images.Repository;

import com.example.OceanNews.Modules.Images.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {
}
