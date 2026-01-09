package com.pos.system.service.Core;
import com.pos.system.entity.Core.InventoryTransaction;

import java.util.List;
import java.util.Optional;

public interface InventoryTransactionService {
    InventoryTransaction save(InventoryTransaction transaction);
    Optional<InventoryTransaction> findById(Long id);
    List<InventoryTransaction> findByBranchId(Long branchId);
}
