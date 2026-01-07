package com.pos.system.service.impl;

import com.pos.system.entity.Audit.ChequeLedger;
import com.pos.system.repository.ledger.ChequeLedgerRepository;
import com.pos.system.service.ChequeLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChequeLedgerServiceImpl implements ChequeLedgerService {

    private final ChequeLedgerRepository repository;

    @Override
    public ChequeLedger save(ChequeLedger ledger) {
        return repository.save(ledger);
    }

    @Override
    public Optional<ChequeLedger> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ChequeLedger> findByChequeNumber(String chequeNumber) {
        return repository.findByChequeNumber(chequeNumber);
    }

    @Override
    public List<ChequeLedger> findAll() {
        return repository.findAll();
    }
}