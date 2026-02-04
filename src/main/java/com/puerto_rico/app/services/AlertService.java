package com.puerto_rico.app.services;

import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import com.puerto_rico.app.entities.Alert;
import com.puerto_rico.app.repositories.AlertRepository;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final AuditService auditService;

    public AlertService(AlertRepository alertRepository, AuditService auditService) {
        this.alertRepository = alertRepository;
        this.auditService = auditService;
    }

    public List<Alert> findActive() {
        return alertRepository.findByActiveTrueOrderByCreatedAtDesc();
    }

    public Alert create(Alert request) {
        Alert alert = new Alert();
        alert.setTitle(request.getTitle());
        alert.setMessage(request.getMessage());
        alert.setType(request.getType());
        alert.setActive(request.isActive());

        Alert saved = alertRepository.save(alert);
        auditService.log("Alert", "CREATE", SecurityUtils.currentUsername());
        return saved;
    }

    public Alert update(Long id, Alert request) {
        Alert alert = alertRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Alerta no encontrada"));

        alert.setTitle(request.getTitle());
        alert.setMessage(request.getMessage());
        alert.setType(request.getType());
        alert.setActive(request.isActive());

        Alert saved = alertRepository.save(alert);
        auditService.log("Alert", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }
}
