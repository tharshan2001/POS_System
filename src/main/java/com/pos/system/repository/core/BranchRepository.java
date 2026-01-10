package com.pos.system.repository.core;


import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.BranchType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    Optional<Branch> findByName(String name);
    boolean existsByName(String name);
    boolean existsByType(BranchType type);
}