package com.pos.system.service.Audit;
import com.pos.system.entity.Audit.CreditLedger;

import java.util.List;
import java.util.Optional;

public interface CreditLedgerService {
    CreditLedger save(CreditLedger ledger);
    Optional<CreditLedger> findById(Long id);
    List<CreditLedger> findByCustomerId(Long customerId);
    List<CreditLedger> findByBranchId(Long branchId);
}
