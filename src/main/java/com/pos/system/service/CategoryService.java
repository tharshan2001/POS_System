package com.pos.system.service;

import com.pos.system.entity.Lookup.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
    List<Category> findAll();
    void deleteById(Long id);
}