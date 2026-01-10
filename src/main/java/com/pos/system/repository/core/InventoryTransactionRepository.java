package com.pos.system.repository.core;

import com.pos.system.entity.Core.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findByFromBranch_Id(Long branchId);

    List<InventoryTransaction> findByToBranch_Id(Long branchId);
}