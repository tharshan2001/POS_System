package com.pos.system.entity.Core;

import com.pos.system.entity.Lookup.TransactionType;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Source branch (from)
    @ManyToOne
    @JoinColumn(name = "from_branch_id")
    private Branch fromBranch;

    // Destination branch (to)
    @ManyToOne
    @JoinColumn(name = "to_branch_id")
    private Branch toBranch;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime createdAt;

    @Column(length = 500)
    private String notes;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}