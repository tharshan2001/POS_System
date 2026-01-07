package com.pos.system.entity.Purchases;

import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_returns")
@Getter
@Setter
public class PurchaseReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private PurchaseItem purchaseItem;
    @ManyToOne private User createdBy;

    private Integer quantity;
    private LocalDateTime returnDate;
}