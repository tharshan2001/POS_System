package com.pos.system.service.Core;
import com.pos.system.entity.Core.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByCategoryId(Long categoryId);
    void deleteById(Long id);
}