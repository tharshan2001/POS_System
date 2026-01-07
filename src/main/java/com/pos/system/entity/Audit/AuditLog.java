package com.pos.system.entity.Audit;

import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter @Setter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String action;
    private String tableName;
    private Long recordId;

    @Column(columnDefinition = "json")
    private String oldData;

    @Column(columnDefinition = "json")
    private String newData;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}