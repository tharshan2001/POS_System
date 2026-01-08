package com.pos.system.controller.lookup;

import com.pos.system.entity.Lookup.TransactionType;
import com.pos.system.repository.lookup.TransactionTypeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-types")
public class TransactionTypeController {

    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeController(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @GetMapping
    public List<TransactionType> getAll() {
        return transactionTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public TransactionType getById(@PathVariable Long id) {
        return transactionTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction Type not found"));
    }
}