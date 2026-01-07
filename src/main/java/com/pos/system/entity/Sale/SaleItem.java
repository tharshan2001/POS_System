package com.pos.system.entity.Sale;

import com.pos.system.entity.Core.Product;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "sale_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sale_id", "product_id"})
)
@Getter @Setter
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Sale sale;
    @ManyToOne private Product product;

    private Integer quantity;
    private Double price;
}