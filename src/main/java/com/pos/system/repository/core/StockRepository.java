package com.pos.system.repository.core;

import com.pos.system.entity.Core.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByBranchIdAndProductId(Long branchId, Long productId);

    List<Stock> findByBranchId(Long branchId);
}