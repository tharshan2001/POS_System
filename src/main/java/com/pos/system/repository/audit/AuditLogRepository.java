package com.pos.system.repository.audit;

import com.pos.system.entity.Audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByUserId(Long userId);
    List<AuditLog> findByTableName(String tableName);
}
