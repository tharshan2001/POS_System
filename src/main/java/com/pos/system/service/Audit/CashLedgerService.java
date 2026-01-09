package com.pos.system.service.Audit;

import com.pos.system.entity.Audit.CashLedger;

import java.util.List;
import java.util.Optional;

public interface CashLedgerService {
    CashLedger save(CashLedger ledger);
    Optional<CashLedger> findById(Long id);
    List<CashLedger> findByBranchId(Long branchId);
}