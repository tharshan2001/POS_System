package com.pos.system.entity.Audit;


import com.pos.system.entity.Lookup.Status;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cheque_ledgers")
public class ChequeLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String chequeNumber;

    private LocalDateTime transactionDate;

    @Column(name = "reference_type_id", nullable = false)
    private Integer referenceTypeId;

    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    private String bankName;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }
}