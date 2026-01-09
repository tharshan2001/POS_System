package com.pos.system.entity.Purchases;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.PaymentMethod;
import com.pos.system.entity.people.Supplier;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private PaymentMethod paymentMethod;

    @ManyToOne
    private User createdBy;

    private Double totalAmount;
    private LocalDate purchaseDate;
    private LocalDate dueDate;

    @Column(unique = true)
    private String invoiceNumber;
}