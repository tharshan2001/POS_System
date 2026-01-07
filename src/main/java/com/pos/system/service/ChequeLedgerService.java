package com.pos.system.service;

import com.pos.system.entity.Audit.ChequeLedger;

import java.util.List;
import java.util.Optional;

public interface ChequeLedgerService {
    ChequeLedger save(ChequeLedger ledger);
    Optional<ChequeLedger> findById(Long id);
    Optional<ChequeLedger> findByChequeNumber(String chequeNumber);
    List<ChequeLedger> findAll();
}