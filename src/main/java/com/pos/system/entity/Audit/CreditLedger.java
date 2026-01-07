package com.pos.system.entity.Audit;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.people.Customer;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "credit_ledgers")
public class CreditLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(name = "reference_type_id", nullable = false)
    private Integer referenceTypeId;

    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    private LocalDateTime transactionDate;

    private Double amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }
}