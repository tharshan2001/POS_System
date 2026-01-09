package com.pos.system.impl.Sale;


import com.pos.system.entity.Sale.SalesReturn;
import com.pos.system.repository.sales.SalesReturnRepository;
import com.pos.system.service.Sale.SalesReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesReturnServiceImpl implements SalesReturnService {

    private final SalesReturnRepository salesReturnRepository;

    @Override
    public SalesReturn save(SalesReturn salesReturn) {
        return salesReturnRepository.save(salesReturn);
    }

    @Override
    public Optional<SalesReturn> findById(Long id) {
        return salesReturnRepository.findById(id);
    }

    @Override
    public List<SalesReturn> findBySaleItemId(Long saleItemId) {
        return salesReturnRepository.findBySaleItemId(saleItemId);
    }

    @Override
    public void deleteById(Long id) {
        salesReturnRepository.deleteById(id);
    }
}