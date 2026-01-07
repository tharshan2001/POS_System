package com.pos.system.service;


import com.pos.system.entity.Audit.AuditLog;

import java.util.List;
import java.util.Optional;

public interface AuditLogService {
    AuditLog save(AuditLog auditLog);
    Optional<AuditLog> findById(Long id);
    List<AuditLog> findByUserId(Long userId);
    List<AuditLog> findByTableName(String tableName);
}