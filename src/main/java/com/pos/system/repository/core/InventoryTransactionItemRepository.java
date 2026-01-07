package com.pos.system.repository.core;

import com.pos.system.entity.Core.InventoryTransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTransactionItemRepository
        extends JpaRepository<InventoryTransactionItem, Long> {

    List<InventoryTransactionItem> findByInventoryTransactionId(Long transactionId);
}