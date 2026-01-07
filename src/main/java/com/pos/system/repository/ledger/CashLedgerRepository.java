package com.pos.system.repository.ledger;

import com.pos.system.entity.Audit.CashLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashLedgerRepository extends JpaRepository<CashLedger, Long> {

    List<CashLedger> findByBranchId(Long branchId);
}
