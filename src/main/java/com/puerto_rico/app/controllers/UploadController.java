package com.puerto_rico.app.controllers;

import com.puerto_rico.app.core.ApiResponse;
import com.puerto_rico.app.dto.UploadResponse;
import com.puerto_rico.app.services.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<UploadResponse>> upload(@RequestParam("file") MultipartFile file)
        throws IOException {
        String url = uploadService.uploadImage(file);
        return ResponseEntity.ok(ApiResponse.ok("Imagen subida", new UploadResponse(url)));
    }
}
