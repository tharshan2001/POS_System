package com.pos.system.service.impl;


import com.pos.system.entity.Audit.Grn;
import com.pos.system.repository.audit.GrnRepository;
import com.pos.system.service.GrnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GrnServiceImpl implements GrnService {

    private final GrnRepository grnRepository;

    @Override
    public Grn save(Grn grn) {
        return grnRepository.save(grn);
    }

    @Override
    public Optional<Grn> findById(Long id) {
        return grnRepository.findById(id);
    }

    @Override
    public Optional<Grn> findByGrnNumber(String grnNumber) {
        return grnRepository.findByGrnNumber(grnNumber);
    }

    @Override
    public List<Grn> findByFromBranch(Long branchId) {
        return grnRepository.findByFromBranchId(branchId);
    }

    @Override
    public List<Grn> findByToBranch(Long branchId) {
        return grnRepository.findByToBranchId(branchId);
    }
}
