package com.puerto_rico.app.services;

import com.puerto_rico.app.entities.AuditLog;
import com.puerto_rico.app.repositories.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void log(String entity, String action, String username) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntity(entity);
        auditLog.setAction(action);
        auditLog.setUsername(username);
        auditLogRepository.save(auditLog);
    }
}
