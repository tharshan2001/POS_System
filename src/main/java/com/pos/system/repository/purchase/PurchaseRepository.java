package com.pos.system.repository.purchase;

import com.pos.system.entity.Purchases.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Optional<Purchase> findByInvoiceNumber(String invoiceNumber);

    // Corrected to reference the supplier entity's ID
    List<Purchase> findBySupplier_Id(Long supplierId);

    // Corrected to reference the branch entity's ID
    List<Purchase> findByBranch_Id(Long branchId);
}