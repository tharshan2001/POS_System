package com.pos.system.service.impl;


import com.pos.system.entity.Purchases.PurchaseReturn;
import com.pos.system.repository.purchase.PurchaseReturnRepository;
import com.pos.system.service.PurchaseReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseReturnServiceImpl implements PurchaseReturnService {

    private final PurchaseReturnRepository purchaseReturnRepository;

    @Override
    public PurchaseReturn save(PurchaseReturn purchaseReturn) {
        return purchaseReturnRepository.save(purchaseReturn);
    }

    @Override
    public Optional<PurchaseReturn> findById(Long id) {
        return purchaseReturnRepository.findById(id);
    }

    @Override
    public List<PurchaseReturn> findByPurchaseItemId(Long purchaseItemId) {
        return purchaseReturnRepository.findByPurchaseItemId(purchaseItemId);
    }

    @Override
    public void deleteById(Long id) {
        purchaseReturnRepository.deleteById(id);
    }
}
