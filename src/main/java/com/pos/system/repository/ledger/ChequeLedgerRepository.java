package com.pos.system.repository.ledger;

import com.pos.system.entity.Audit.ChequeLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChequeLedgerRepository extends JpaRepository<ChequeLedger, Long> {

    Optional<ChequeLedger> findByChequeNumber(String chequeNumber);
}
