package com.puerto_rico.app.services;

import com.puerto_rico.app.entities.EmergencyContact;
import com.puerto_rico.app.repositories.EmergencyContactRepository;
import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactService {

    private final EmergencyContactRepository contactRepository;
    private final AuditService auditService;

    public EmergencyContactService(EmergencyContactRepository contactRepository, AuditService auditService) {
        this.contactRepository = contactRepository;
        this.auditService = auditService;
    }

    public List<EmergencyContact> findActive() {
        return contactRepository.findByActiveTrueOrderByPriorityAsc();
    }

    public EmergencyContact create(EmergencyContact request) {
        EmergencyContact contact = new EmergencyContact();
        contact.setName(request.getName());
        contact.setPhone(request.getPhone());
        contact.setIcon(request.getIcon());
        contact.setPriority(request.getPriority());
        contact.setActive(request.isActive());

        EmergencyContact saved = contactRepository.save(contact);
        auditService.log("EmergencyContact", "CREATE", SecurityUtils.currentUsername());
        return saved;
    }

    public EmergencyContact update(Long id, EmergencyContact request) {
        EmergencyContact contact = contactRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado"));

        contact.setName(request.getName());
        contact.setPhone(request.getPhone());
        contact.setIcon(request.getIcon());
        contact.setPriority(request.getPriority());
        contact.setActive(request.isActive());

        EmergencyContact saved = contactRepository.save(contact);
        auditService.log("EmergencyContact", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }
}
