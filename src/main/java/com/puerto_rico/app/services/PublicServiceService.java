package com.puerto_rico.app.services;

import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import com.puerto_rico.app.entities.PublicService;
import com.puerto_rico.app.repositories.PublicServiceRepository;

@Service
public class PublicServiceService {

    private final PublicServiceRepository serviceRepository;
    private final AuditService auditService;

    public PublicServiceService(PublicServiceRepository serviceRepository, AuditService auditService) {
        this.serviceRepository = serviceRepository;
        this.auditService = auditService;
    }

    public List<PublicService> findActive() {
        return serviceRepository.findByActiveTrue();
    }

    public PublicService create(PublicService request) {
        PublicService service = new PublicService();
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setSchedule(request.getSchedule());
        service.setActive(request.isActive());

        PublicService saved = serviceRepository.save(service);
        auditService.log("PublicService", "CREATE", SecurityUtils.currentUsername());
        return saved;
    }

    public PublicService update(Long id, PublicService request) {
        PublicService service = serviceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));

        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setSchedule(request.getSchedule());
        service.setActive(request.isActive());

        PublicService saved = serviceRepository.save(service);
        auditService.log("PublicService", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }
}
