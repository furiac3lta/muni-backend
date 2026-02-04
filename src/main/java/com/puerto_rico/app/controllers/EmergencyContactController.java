package com.puerto_rico.app.controllers;

import com.puerto_rico.app.core.ApiResponse;
import com.puerto_rico.app.entities.EmergencyContact;
import com.puerto_rico.app.services.EmergencyContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class EmergencyContactController {

    private final EmergencyContactService contactService;

    public EmergencyContactController(EmergencyContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/api/contacts")
    public ResponseEntity<ApiResponse<List<EmergencyContact>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Contactos", contactService.findActive()));
    }

    @PostMapping("/api/admin/contacts")
    public ResponseEntity<ApiResponse<EmergencyContact>> create(@RequestBody EmergencyContact request) {
        return ResponseEntity.ok(ApiResponse.ok("Contacto creado", contactService.create(request)));
    }

    @PutMapping("/api/admin/contacts/{id}")
    public ResponseEntity<ApiResponse<EmergencyContact>> update(
        @PathVariable Long id,
        @RequestBody EmergencyContact request
    ) {
        return ResponseEntity.ok(ApiResponse.ok("Contacto actualizado", contactService.update(id, request)));
    }
}
