package com.pos.system.entity.Audit;


import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cash_ledgers")
public class CashLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime transactionDate;

    @Column(name = "transaction_type_id", nullable = false)
    private Integer transactionTypeId;

    @Column(name = "reference_type_id", nullable = false)
    private Integer referenceTypeId;

    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }
}