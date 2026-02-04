package com.puerto_rico.app.services;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadService {

    private final Cloudinary cloudinary;

    public UploadService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(
            file.getBytes(),
            Map.of("folder", "municipio")
        );
        Object secureUrl = result.get("secure_url");
        return secureUrl != null ? secureUrl.toString() : result.get("url").toString();
    }
}
