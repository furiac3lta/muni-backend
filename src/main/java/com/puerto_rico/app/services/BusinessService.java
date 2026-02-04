package com.puerto_rico.app.services;

import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import com.puerto_rico.app.entities.Business;
import com.puerto_rico.app.repositories.BusinessRepository;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final AuditService auditService;

    public BusinessService(BusinessRepository businessRepository, AuditService auditService) {
        this.businessRepository = businessRepository;
        this.auditService = auditService;
    }

    public List<Business> findActive() {
        return businessRepository.findByActiveTrue();
    }

    public Business create(Business request) {
        Business business = new Business();
        business.setName(request.getName());
        business.setCategory(request.getCategory());
        business.setPhone(request.getPhone());
        business.setWhatsapp(request.getWhatsapp());
        business.setAddress(request.getAddress());
        business.setHighlighted(request.isHighlighted());
        business.setActive(request.isActive());

        Business saved = businessRepository.save(business);
        auditService.log("Business", "CREATE", SecurityUtils.currentUsername());
        return saved;
    }

    public Business update(Long id, Business request) {
        Business business = businessRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comercio no encontrado"));

        business.setName(request.getName());
        business.setCategory(request.getCategory());
        business.setPhone(request.getPhone());
        business.setWhatsapp(request.getWhatsapp());
        business.setAddress(request.getAddress());
        business.setHighlighted(request.isHighlighted());
        business.setActive(request.isActive());

        Business saved = businessRepository.save(business);
        auditService.log("Business", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }
}
