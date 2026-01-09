package com.pos.system.impl;

import com.pos.system.entity.Audit.AuditLog;
import com.pos.system.repository.audit.AuditLogRepository;
import com.pos.system.service.Audit.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository repository;

    @Override
    public AuditLog save(AuditLog auditLog) {
        return repository.save(auditLog);
    }

    @Override
    public Optional<AuditLog> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<AuditLog> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<AuditLog> findByTableName(String tableName) {
        return repository.findByTableName(tableName);
    }
}