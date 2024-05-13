package com.example.OceanNews.Modules.Images.Controller;

import com.example.OceanNews.Modules.Images.Images;
import com.example.OceanNews.Modules.Images.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/newsApp/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @PostMapping("/images/add")
    public ResponseEntity<Images> addImage(@RequestBody Images details, @RequestParam("file") MultipartFile file) throws IOException {
        Images savedImage = imageService.add(details, file);
        return ResponseEntity.ok(savedImage);
    }
    @PostMapping("/file/add")
    public ResponseEntity<Images> addFile(@RequestBody Images details, @RequestParam("file") InputStream file) throws IOException {
        Images savedImage = imageService.addFile(details, file);
        return ResponseEntity.ok(savedImage);
    }
}
