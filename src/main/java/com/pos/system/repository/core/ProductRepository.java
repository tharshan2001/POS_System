package com.pos.system.repository.core;

import com.pos.system.entity.Core.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByStatusId(Long statusId);
}