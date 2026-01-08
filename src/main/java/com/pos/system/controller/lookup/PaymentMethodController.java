package com.pos.system.controller.lookup;

import com.pos.system.entity.Lookup.PaymentMethod;
import com.pos.system.repository.lookup.PaymentMethodRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodController(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @GetMapping
    public List<PaymentMethod> getAll() {
        return paymentMethodRepository.findAll();
    }

    @GetMapping("/{id}")
    public PaymentMethod getById(@PathVariable Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment Method not found"));
    }
}