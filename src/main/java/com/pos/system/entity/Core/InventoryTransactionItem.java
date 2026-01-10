package com.pos.system.entity.Core;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "inventory_transaction_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"inventory_transaction_id", "product_id"})
)
@Getter
@Setter
public class InventoryTransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private InventoryTransaction inventoryTransaction;
    @ManyToOne private Product product;

    private Integer quantity;
}
