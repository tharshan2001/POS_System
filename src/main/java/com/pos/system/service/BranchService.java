package com.pos.system.service;

import com.pos.system.entity.Core.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    Branch save(Branch branch);
    Optional<Branch> findById(Long id);
    List<Branch> findAll();
    Optional<Branch> findByName(String name);
}