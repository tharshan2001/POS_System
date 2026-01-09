package com.pos.system.impl.Audit;


import com.pos.system.entity.Audit.CreditLedger;
import com.pos.system.repository.ledger.CreditLedgerRepository;
import com.pos.system.service.Audit.CreditLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditLedgerServiceImpl implements CreditLedgerService {

    private final CreditLedgerRepository repository;

    @Override
    public CreditLedger save(CreditLedger ledger) {
        return repository.save(ledger);
    }

    @Override
    public Optional<CreditLedger> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CreditLedger> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<CreditLedger> findByBranchId(Long branchId) {
        return repository.findByBranchId(branchId);
    }
}