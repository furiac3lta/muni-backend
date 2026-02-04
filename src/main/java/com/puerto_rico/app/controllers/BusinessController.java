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
import com.puerto_rico.app.entities.Business;
import com.puerto_rico.app.services.BusinessService;

@RestController
@RequestMapping
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("/api/business")
    public ResponseEntity<ApiResponse<List<Business>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Comercios", businessService.findActive()));
    }

    @PostMapping("/api/admin/business")
    public ResponseEntity<ApiResponse<Business>> create(@RequestBody Business request) {
        return ResponseEntity.ok(ApiResponse.ok("Comercio creado", businessService.create(request)));
    }

    @PutMapping("/api/admin/business/{id}")
    public ResponseEntity<ApiResponse<Business>> update(
        @PathVariable Long id,
        @RequestBody Business request
    ) {
        return ResponseEntity.ok(ApiResponse.ok("Comercio actualizado", businessService.update(id, request)));
    }
}
