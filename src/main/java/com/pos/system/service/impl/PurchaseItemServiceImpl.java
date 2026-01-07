package com.pos.system.service.impl;

import com.pos.system.entity.Purchases.PurchaseItem;
import com.pos.system.repository.purchase.PurchaseItemRepository;
import com.pos.system.service.PurchaseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

    @Override
    public PurchaseItem save(PurchaseItem item) {
        return purchaseItemRepository.save(item);
    }

    @Override
    public Optional<PurchaseItem> findById(Long id) {
        return purchaseItemRepository.findById(id);
    }

    @Override
    public List<PurchaseItem> findByPurchaseId(Long purchaseId) {
        return purchaseItemRepository.findByPurchaseId(purchaseId);
    }

    @Override
    public void deleteById(Long id) {
        purchaseItemRepository.deleteById(id);
    }
}