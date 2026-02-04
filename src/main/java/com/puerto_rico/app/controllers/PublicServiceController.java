package com.puerto_rico.app.controllers;

import com.puerto_rico.app.core.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.puerto_rico.app.entities.PublicService;
import com.puerto_rico.app.services.PublicServiceService;

@RestController
@RequestMapping
public class PublicServiceController {

    private final PublicServiceService publicServiceService;

    public PublicServiceController(PublicServiceService publicServiceService) {
        this.publicServiceService = publicServiceService;
    }

    @GetMapping("/api/services")
    public ResponseEntity<ApiResponse<List<PublicService>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Servicios", publicServiceService.findActive()));
    }

    @PostMapping("/api/admin/services")
    public ResponseEntity<ApiResponse<PublicService>> create(@RequestBody PublicService request) {
        return ResponseEntity.ok(ApiResponse.ok("Servicio creado", publicServiceService.create(request)));
    }

    @PutMapping("/api/admin/services/{id}")
    public ResponseEntity<ApiResponse<PublicService>> update(
        @PathVariable Long id,
        @RequestBody PublicService request
    ) {
        return ResponseEntity.ok(ApiResponse.ok("Servicio actualizado", publicServiceService.update(id, request)));
    }
}
