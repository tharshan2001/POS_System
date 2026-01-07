package com.pos.system.repository.ledger;

import com.pos.system.entity.Audit.CreditLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditLedgerRepository extends JpaRepository<CreditLedger, Long> {

    List<CreditLedger> findByCustomerId(Long customerId);
    List<CreditLedger> findByBranchId(Long branchId);
}