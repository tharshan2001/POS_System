package com.pos.system.service;

import com.pos.system.entity.Lookup.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod createIfNotExists(String name);
}