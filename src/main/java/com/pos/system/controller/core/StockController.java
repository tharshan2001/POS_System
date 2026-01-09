package com.pos.system.controller.core;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Core.Product;
import com.pos.system.entity.Core.Stock;
import com.pos.system.repository.core.BranchRepository;
import com.pos.system.repository.core.ProductRepository;
import com.pos.system.repository.core.StockRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockRepository stockRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public StockController(StockRepository stockRepository,
                           BranchRepository branchRepository,
                           ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    // Get all stock
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockRepository.findAll());
    }

    // Get stock by branch
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Stock>> getStockByBranch(@PathVariable Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        return ResponseEntity.ok(stockRepository.findByBranch(branch));
    }

    // Get stock for a specific product in a branch
    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<Stock> getStockForProduct(
            @PathVariable Long branchId,
            @PathVariable Long productId) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Stock stock = stockRepository.findByBranchAndProduct(branch, product)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        return ResponseEntity.ok(stock);
    }
}