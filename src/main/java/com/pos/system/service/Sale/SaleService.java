package com.pos.system.service;

import com.pos.system.entity.Sale.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    Sale save(Sale sale);
    Optional<Sale> findById(Long id);
    Optional<Sale> findByInvoiceNumber(String invoiceNumber);
    List<Sale> findByBranchId(Long branchId);
    List<Sale> findByCustomerId(Long customerId);
    void deleteById(Long id);
}