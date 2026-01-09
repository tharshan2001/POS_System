package com.pos.system.service;

import com.pos.system.entity.Purchases.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    Purchase save(Purchase purchase);
    Optional<Purchase> findById(Long id);
    Optional<Purchase> findByInvoiceNumber(String invoiceNumber);
    List<Purchase> findBySupplierId(Long supplierId);
    List<Purchase> findByBranchId(Long branchId);
    void deleteById(Long id);
}