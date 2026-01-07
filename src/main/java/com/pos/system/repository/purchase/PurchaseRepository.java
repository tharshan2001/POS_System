package com.pos.system.repository.purchase;

import com.pos.system.entity.Purchases.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Optional<Purchase> findByInvoiceNumber(String invoiceNumber);

    List<Purchase> findBySupplierId(Long supplierId);
    List<Purchase> findByBranchId(Long branchId);
}