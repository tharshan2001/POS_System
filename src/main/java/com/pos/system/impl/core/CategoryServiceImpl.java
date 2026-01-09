package com.pos.system.impl.core;



import com.pos.system.entity.Lookup.Category;
import com.pos.system.repository.core.CategoryRepository;
import com.pos.system.service.Core.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category createIfNotExists(String name) {
        return categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(name);
                    return categoryRepository.save(category);
                });
    }
}