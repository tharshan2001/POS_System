package com.pos.system.entity.Audit;

import com.pos.system.entity.Core.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(
        name = "grn_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"grn_id", "product_id"})
)
public class GrnItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grn_id", nullable = false)
    private Grn grn;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
}