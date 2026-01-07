package com.pos.system.service.impl;


import com.pos.system.entity.Core.InventoryTransaction;
import com.pos.system.repository.core.InventoryTransactionRepository;
import com.pos.system.service.InventoryTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryTransactionServiceImpl implements InventoryTransactionService {

    private final InventoryTransactionRepository repository;

    @Override
    public InventoryTransaction save(InventoryTransaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Optional<InventoryTransaction> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<InventoryTransaction> findByBranchId(Long branchId) {
        return repository.findByBranchId(branchId);
    }
}