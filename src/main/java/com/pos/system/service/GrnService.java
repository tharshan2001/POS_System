package com.pos.system.service;

import com.pos.system.entity.Audit.Grn;

import java.util.List;
import java.util.Optional;

public interface GrnService {
    Grn save(Grn grn);
    Optional<Grn> findById(Long id);
    Optional<Grn> findByGrnNumber(String grnNumber);
    List<Grn> findByFromBranch(Long branchId);
    List<Grn> findByToBranch(Long branchId);
}