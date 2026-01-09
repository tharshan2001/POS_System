package com.pos.system.impl.Sale;


import com.pos.system.entity.Sale.Sale;
import com.pos.system.repository.sales.SaleRepository;
import com.pos.system.service.Sale.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    public Optional<Sale> findByInvoiceNumber(String invoiceNumber) {
        return saleRepository.findByInvoiceNumber(invoiceNumber);
    }

    @Override
    public List<Sale> findByBranchId(Long branchId) {
        return saleRepository.findByBranchId(branchId);
    }

    @Override
    public List<Sale> findByCustomerId(Long customerId) {
        return saleRepository.findByCustomerId(customerId);
    }

    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }
}
