package com.pos.system.impl;

import com.pos.system.entity.Lookup.BranchType;
import com.pos.system.repository.core.BranchTypeRepository;
import com.pos.system.service.Lookup.BranchTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchTypeServiceImpl implements BranchTypeService {

    private final BranchTypeRepository branchTypeRepository;

    @Override
    public BranchType create(String name) {
        if (branchTypeRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Branch type already exists: " + name);
        }

        BranchType branchType = new BranchType();
        branchType.setName(name);

        return branchTypeRepository.save(branchType);
    }

    @Override
    public BranchType createIfNotExists(String name) {
        return branchTypeRepository.findByName(name)
                .orElseGet(() -> {
                    BranchType branchType = new BranchType();
                    branchType.setName(name);
                    return branchTypeRepository.save(branchType);
                });
    }

    @Override
    public BranchType getById(Long id) {
        return branchTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch type not found with id: " + id));
    }

    @Override
    public BranchType getByName(String name) {
        return branchTypeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Branch type not found: " + name));
    }

    @Override
    public List<BranchType> getAll() {
        return branchTypeRepository.findAll();
    }
}