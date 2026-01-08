package com.pos.system.entity.Lookup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // Default constructor for Hibernate
    public PaymentMethod() {}

    // Convenient constructor
    public PaymentMethod(String name) {
        this.name = name;
    }
}