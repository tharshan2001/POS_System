package com.pos.system.repository.core;

import com.pos.system.entity.Core.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findByBranchId(Long branchId);
}