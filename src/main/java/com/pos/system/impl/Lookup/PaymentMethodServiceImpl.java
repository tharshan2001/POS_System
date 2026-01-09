package com.pos.system.impl.Lookup;

import com.pos.system.entity.Lookup.PaymentMethod;
import com.pos.system.repository.lookup.PaymentMethodRepository;
import com.pos.system.service.Lookup.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod createIfNotExists(String name) {
        // Check if payment method exists, else create
        return paymentMethodRepository.findByName(name)
                .orElseGet(() -> paymentMethodRepository.save(new PaymentMethod(name)));
    }
}