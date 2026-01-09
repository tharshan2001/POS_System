package com.pos.system.service.purchases;

import com.pos.system.entity.Purchases.PurchaseItem;

import java.util.List;
import java.util.Optional;

public interface PurchaseItemService {
    PurchaseItem save(PurchaseItem item);
    Optional<PurchaseItem> findById(Long id);
    List<PurchaseItem> findByPurchaseId(Long purchaseId);
    void deleteById(Long id);
}