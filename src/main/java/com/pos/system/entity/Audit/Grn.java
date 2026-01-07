package com.pos.system.entity.Audit;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.Status;
import com.pos.system.entity.Lookup.TransactionType;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "grns")
public class Grn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String grnNumber;

    @ManyToOne
    @JoinColumn(name = "from_branch_id", nullable = false)
    private Branch fromBranch;

    @ManyToOne
    @JoinColumn(name = "to_branch_id", nullable = false)
    private Branch toBranch;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime receivedAt;
    private String notes;

    @OneToMany(mappedBy = "grn", cascade = CascadeType.ALL)
    private List<GrnItem> items;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}