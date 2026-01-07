package com.pos.system.service.impl;


import com.pos.system.entity.Purchases.Purchase;
import com.pos.system.repository.purchase.PurchaseRepository;
import com.pos.system.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Optional<Purchase> findByInvoiceNumber(String invoiceNumber) {
        return purchaseRepository.findByInvoiceNumber(invoiceNumber);
    }

    @Override
    public List<Purchase> findBySupplierId(Long supplierId) {
        return purchaseRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<Purchase> findByBranchId(Long branchId) {
        return purchaseRepository.findByBranchId(branchId);
    }

    @Override
    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }
}