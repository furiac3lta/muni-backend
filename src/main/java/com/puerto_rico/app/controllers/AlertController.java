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
import com.puerto_rico.app.entities.Alert;
import com.puerto_rico.app.services.AlertService;

@RestController
@RequestMapping
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/api/alerts")
    public ResponseEntity<ApiResponse<List<Alert>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Alertas", alertService.findActive()));
    }

    @PostMapping("/api/admin/alerts")
    public ResponseEntity<ApiResponse<Alert>> create(@RequestBody Alert request) {
        return ResponseEntity.ok(ApiResponse.ok("Alerta creada", alertService.create(request)));
    }

    @PutMapping("/api/admin/alerts/{id}")
    public ResponseEntity<ApiResponse<Alert>> update(@PathVariable Long id, @RequestBody Alert request) {
        return ResponseEntity.ok(ApiResponse.ok("Alerta actualizada", alertService.update(id, request)));
    }
}
