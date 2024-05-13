package com.example.OceanNews.Modules.Images.Service;

import com.example.OceanNews.Modules.Images.Images;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public interface ImageService {
    Images add(Images details, MultipartFile image) throws IOException;
    Iterable<Images> getAll();
    void softDelete(Long ID);
    void hardDelete(Long ID);
    void restore(Long ID);
    Images getById(Long ID);
    Images addFile(Images details, InputStream img) throws IOException;
}
