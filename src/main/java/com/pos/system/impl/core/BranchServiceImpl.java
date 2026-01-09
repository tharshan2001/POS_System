package com.pos.system.impl.core;


import com.pos.system.entity.Core.Branch;
import com.pos.system.repository.core.BranchRepository;
import com.pos.system.service.Core.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Optional<Branch> findById(Long id) {
        return branchRepository.findById(id);
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Optional<Branch> findByName(String name) {
        return branchRepository.findByName(name);
    }
}