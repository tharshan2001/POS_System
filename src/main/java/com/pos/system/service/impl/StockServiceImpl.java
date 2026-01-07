package com.pos.system.service.impl;


import com.pos.system.entity.Core.Stock;
import com.pos.system.repository.core.StockRepository;
import com.pos.system.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return stockRepository.findById(id);
    }

    @Override
    public Optional<Stock> findByBranchAndProduct(Long branchId, Long productId) {
        return stockRepository.findByBranchIdAndProductId(branchId, productId);
    }

    @Override
    public List<Stock> findByBranchId(Long branchId) {
        return stockRepository.findByBranchId(branchId);
    }
}