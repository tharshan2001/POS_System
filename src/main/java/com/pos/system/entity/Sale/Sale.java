package com.pos.system.entity.Sale;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.PaymentMethod;
import com.pos.system.entity.people.Customer;
import com.pos.system.entity.people.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "sales")
@Getter
@Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Branch branch;
    @ManyToOne private Customer customer;
    @ManyToOne private User user;
    @ManyToOne private PaymentMethod paymentMethod;

    private Double totalAmount;
    private LocalDateTime saleDate;
    private LocalDate dueDate;

    @Column(unique = true)
    private String invoiceNumber;
}
