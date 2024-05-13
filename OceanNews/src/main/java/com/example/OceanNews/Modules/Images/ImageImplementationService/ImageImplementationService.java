package com.example.OceanNews.Modules.Images.ImageImplementationService;

import com.example.OceanNews.Components.MultipartProperties;
import com.example.OceanNews.Modules.Images.Images;
import com.example.OceanNews.Modules.Images.Repository.ImageRepository;
import com.example.OceanNews.Modules.Images.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageImplementationService implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private MultipartProperties multipartProperties;
    @Override
    public Images add(Images details, MultipartFile imageFile) throws IOException {
      //   Move image file to resources/images folder
        String allowedFileExtensions = multipartProperties.getAllowedFileExtensions();
        String uploadDir = "src/main/resources/images/postImages/";
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = imageFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            details.setName(fileName); // Set image name in Images entity
            details.setFilePath(filePath.toString()); // Set file path in Images entity
            details.setStatus(1L); // Set status to 1
            return imageRepository.save(details); // Save to database and return updated Images object
        } catch (IOException e) {
            throw new IOException("Could not save image file: " + fileName, e);
        }
    }

    @Override
    public Iterable<Images> getAll() {
        return null;
    }

    @Override
    public void softDelete(Long ID) {

    }

    @Override
    public void hardDelete(Long ID) {

    }

    @Override
    public void restore(Long ID) {

    }

    @Override
    public Images getById(Long ID) {
        return null;
    }

    @Override
    public Images addFile(Images details, InputStream img) throws IOException {
        Path path = Paths.get("src/main/resources/images/" + img);
        Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
        details.setFilePath(path.toString());
        imageRepository.save(details);
        return details;
    }
}
