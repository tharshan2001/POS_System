package com.pos.system.repository.core;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Core.Product;
import com.pos.system.entity.Core.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByBranchIdAndProductId(Long branchId, Long productId);

    List<Stock> findByBranchId(Long branchId);

    Optional<Stock> findByBranchAndProduct(Branch branch, Product product);

    List<Stock> findByBranch(Branch branch);

}