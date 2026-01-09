package com.pos.system.service.Core;

import com.pos.system.entity.Core.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {
    Stock save(Stock stock);
    Optional<Stock> findById(Long id);
    Optional<Stock> findByBranchAndProduct(Long branchId, Long productId);
    List<Stock> findByBranchId(Long branchId);
}