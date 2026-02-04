package com.puerto_rico.app.services;

import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.stereotype.Service;
import com.puerto_rico.app.entities.MunicipalInfo;
import com.puerto_rico.app.repositories.MunicipalInfoRepository;

@Service
public class MunicipalInfoService {

    private final MunicipalInfoRepository municipalInfoRepository;
    private final AuditService auditService;

    public MunicipalInfoService(MunicipalInfoRepository municipalInfoRepository, AuditService auditService) {
        this.municipalInfoRepository = municipalInfoRepository;
        this.auditService = auditService;
    }

    public MunicipalInfo getInfo() {
        return municipalInfoRepository.findTopByOrderByUpdatedAtDesc()
            .orElseThrow(() -> new ResourceNotFoundException("Informacion municipal no cargada"));
    }

    public MunicipalInfo updateInfo(MunicipalInfo request) {
        MunicipalInfo info = municipalInfoRepository.findTopByOrderByUpdatedAtDesc().orElse(new MunicipalInfo());
        info.setHistory(request.getHistory());
        info.setAddress(request.getAddress());
        info.setWorkingHours(request.getWorkingHours());
        info.setMapUrl(request.getMapUrl());

        MunicipalInfo saved = municipalInfoRepository.save(info);
        auditService.log("MunicipalInfo", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }
}
