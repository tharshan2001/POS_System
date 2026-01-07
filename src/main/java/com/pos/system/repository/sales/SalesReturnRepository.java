package com.pos.system.repository.sales;

import com.pos.system.entity.Sale.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesReturnRepository extends JpaRepository<SalesReturn, Long> {

    List<SalesReturn> findBySaleItemId(Long saleItemId);
}