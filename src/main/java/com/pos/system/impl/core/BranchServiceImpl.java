package com.pos.system.impl.core;


import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.BranchType;
import com.pos.system.repository.core.BranchRepository;
import com.pos.system.service.Core.BranchService;
import com.pos.system.service.Lookup.BranchTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchTypeService branchTypeService;

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

    @Override
    public boolean existsByType(BranchType type) {
        return branchRepository.existsByType(type);
    }

    @Override
    public void createSystemBranch(Branch branch) {
        branchRepository.save(branch);
    }

    public Branch createRetailBranch(Branch branch) {
        // Check duplicate name
        if (branchRepository.existsByName(branch.getName())) {
            throw new RuntimeException("Branch already exists");
        }

        // Force RETAIL type
        BranchType retailType = branchTypeService.getByName("RETAIL");
        branch.setType(retailType);

        return branchRepository.save(branch);
    }


}