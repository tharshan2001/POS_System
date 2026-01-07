package com.pos.system.entity.Purchases;

import com.pos.system.entity.Core.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "purchase_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"purchase_id", "product_id"})
)
@Getter @Setter
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Purchase purchase;
    @ManyToOne private Product product;

    private Integer quantity;
    private Double unitPrice;
}