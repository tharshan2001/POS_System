package com.pos.system.entity.Sale;

import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name = "sales_returns")
@Getter @Setter
public class SalesReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private SaleItem saleItem;
    @ManyToOne private User createdBy;

    private Integer quantity;
    private Double refundAmount;
    private LocalDateTime returnDate;
}