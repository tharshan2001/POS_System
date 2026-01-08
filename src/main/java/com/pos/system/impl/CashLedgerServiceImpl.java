package com.pos.system.impl;


import com.pos.system.entity.Audit.CashLedger;
import com.pos.system.repository.ledger.CashLedgerRepository;
import com.pos.system.service.CashLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashLedgerServiceImpl implements CashLedgerService {

    private final CashLedgerRepository repository;

    @Override
    public CashLedger save(CashLedger ledger) {
        return repository.save(ledger);
    }

    @Override
    public Optional<CashLedger> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CashLedger> findByBranchId(Long branchId) {
        return repository.findByBranchId(branchId);
    }
}