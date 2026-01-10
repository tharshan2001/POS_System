package com.pos.system.service.Core;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.BranchType;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    Branch save(Branch branch);
    Optional<Branch> findById(Long id);
    List<Branch> findAll();
    Optional<Branch> findByName(String name);
    boolean existsByType(BranchType type);
    void createSystemBranch(Branch branch);

}