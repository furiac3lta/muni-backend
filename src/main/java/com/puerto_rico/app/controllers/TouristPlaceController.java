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
import com.puerto_rico.app.entities.TouristPlace;
import com.puerto_rico.app.services.TouristPlaceService;

@RestController
@RequestMapping
public class TouristPlaceController {

    private final TouristPlaceService placeService;

    public TouristPlaceController(TouristPlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/api/tourism")
    public ResponseEntity<ApiResponse<List<TouristPlace>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Turismo", placeService.findActive()));
    }

    @PostMapping("/api/admin/tourism")
    public ResponseEntity<ApiResponse<TouristPlace>> create(@RequestBody TouristPlace request) {
        return ResponseEntity.ok(ApiResponse.ok("Lugar creado", placeService.create(request)));
    }

    @PutMapping("/api/admin/tourism/{id}")
    public ResponseEntity<ApiResponse<TouristPlace>> update(
        @PathVariable Long id,
        @RequestBody TouristPlace request
    ) {
        return ResponseEntity.ok(ApiResponse.ok("Lugar actualizado", placeService.update(id, request)));
    }
}
