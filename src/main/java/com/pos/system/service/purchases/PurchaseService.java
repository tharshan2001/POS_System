package com.pos.system.service.purchases;

import com.pos.system.dto.purchase.PurchaseDTO;
import com.pos.system.entity.Purchases.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {

    // --- Save an existing purchase entity ---
    Purchase save(Purchase purchase);

    // --- Find purchase by ID ---
    Optional<Purchase> findById(Long id);

    // --- Find purchase by invoice number ---
    Optional<Purchase> findByInvoiceNumber(String invoiceNumber);

    // --- Find purchases by supplier ---
    List<Purchase> findBySupplierId(Long supplierId);

    // --- Find purchases by branch ---
    List<Purchase> findByBranchId(Long branchId);

    // --- Delete purchase ---
    void deleteById(Long id);

    // --- Create a purchase from DTO (updates stock automatically) ---
    Purchase createPurchase(PurchaseDTO purchaseDTO);

    // --- Get purchase by ID (throws exception if not found) ---
    Purchase getPurchaseById(Long id);

    // --- Get all purchases ---
    List<Purchase> getAllPurchases();
}