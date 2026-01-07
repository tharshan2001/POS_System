package com.pos.system.repository.sales;

import com.pos.system.entity.Sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    Optional<Sale> findByInvoiceNumber(String invoiceNumber);

    List<Sale> findByBranchId(Long branchId);
    List<Sale> findByCustomerId(Long customerId);
}