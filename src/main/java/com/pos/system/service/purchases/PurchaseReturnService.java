package com.pos.system.service;

import com.pos.system.entity.Purchases.PurchaseReturn;

import java.util.List;
import java.util.Optional;

public interface PurchaseReturnService {
    PurchaseReturn save(PurchaseReturn purchaseReturn);
    Optional<PurchaseReturn> findById(Long id);
    List<PurchaseReturn> findByPurchaseItemId(Long purchaseItemId);
    void deleteById(Long id);
}