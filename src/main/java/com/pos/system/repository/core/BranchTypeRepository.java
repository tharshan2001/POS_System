package com.pos.system.repository.core;

import com.pos.system.entity.Lookup.BranchType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchTypeRepository extends JpaRepository<BranchType, Long> {
    Optional<BranchType> findByName(String name);
}

