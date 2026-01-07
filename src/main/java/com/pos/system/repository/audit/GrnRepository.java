package com.pos.system.repository.audit;

import com.pos.system.entity.Audit.Grn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrnRepository extends JpaRepository<Grn, Long> {

    Optional<Grn> findByGrnNumber(String grnNumber);

    List<Grn> findByFromBranchId(Long branchId);
    List<Grn> findByToBranchId(Long branchId);
}
