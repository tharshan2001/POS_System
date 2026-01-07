package com.pos.system.entity.Core;

import com.pos.system.entity.Lookup.TransactionType;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name = "inventory_transactions")
@Getter @Setter
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Branch branch;
    @ManyToOne private TransactionType transactionType;
    @ManyToOne private User user;

    private LocalDateTime createdAt;
    private String notes;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}