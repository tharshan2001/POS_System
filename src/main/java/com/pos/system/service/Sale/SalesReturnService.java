package com.pos.system.service;

import com.pos.system.entity.Sale.SalesReturn;

import java.util.List;
import java.util.Optional;

public interface SalesReturnService {
    SalesReturn save(SalesReturn salesReturn);
    Optional<SalesReturn> findById(Long id);
    List<SalesReturn> findBySaleItemId(Long saleItemId);
    void deleteById(Long id);
}