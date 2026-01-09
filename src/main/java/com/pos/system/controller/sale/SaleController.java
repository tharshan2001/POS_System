package com.pos.system.controller.sale;

import com.pos.system.dto.user.SaleRequestDTO;
import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Core.Stock;
import com.pos.system.entity.Sale.Sale;
import com.pos.system.entity.Sale.SaleItem;
import com.pos.system.entity.people.User;
import com.pos.system.entity.people.Customer;
import com.pos.system.repository.core.StockRepository;
import com.pos.system.repository.sales.SaleItemRepository;
import com.pos.system.repository.sales.SaleRepository;
import com.pos.system.repository.people.CustomerRepository;
import com.pos.system.security.FindCurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final StockRepository stockRepository;
    private final CustomerRepository customerRepository;
    private final FindCurrentUser currentUser;

    public SaleController(SaleRepository saleRepository,
                          SaleItemRepository saleItemRepository,
                          StockRepository stockRepository,
                          CustomerRepository customerRepository,
                          FindCurrentUser currentUser) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.stockRepository = stockRepository;
        this.customerRepository = customerRepository;
        this.currentUser = currentUser;
    }

    /**
     * Create Sale
     * Only branch-based users can create sales
     */
    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('CASHIER','SHOP_MANAGER','SUPER_ADMIN')")
    public Sale createSale(@RequestBody SaleRequestDTO request) {

        // 1️⃣ Get logged-in user
        User user = currentUser.getCurrentUser();
        log.info("Creating sale for user: id={}, username={}", user.getId(), user.getUsername());

        // 2️⃣ Validate branch
        Branch branch = user.getBranch();
        if (branch == null) {
            throw new RuntimeException("User is not assigned to any branch");
        }

        // 3️⃣ Get customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found: " + request.getCustomerId()));

        // 4️⃣ Create Sale
        Sale sale = new Sale();
        sale.setUser(user);
        sale.setCustomer(customer);
        sale.setBranch(branch);
        sale.setSaleDate(LocalDateTime.now());

        // 5️⃣ Process Sale Items
        List<SaleItem> saleItems = request.getItems().stream().map(itemDTO -> {

            Stock stock = stockRepository
                    .findByBranchIdAndProductId(branch.getId(), itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found in stock for this branch: " + itemDTO.getProductId()
                    ));

            if (stock.getQuantity() < itemDTO.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + itemDTO.getProductId()
                );
            }

            // Reduce stock
            stock.setQuantity(stock.getQuantity() - itemDTO.getQuantity());
            stockRepository.save(stock);

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(stock.getProduct());
            saleItem.setQuantity(itemDTO.getQuantity());
            saleItem.setPrice(stock.getProduct().getPrice());

            return saleItem;

        }).collect(Collectors.toList());

        // 6️⃣ Calculate total amount
        double totalAmount = saleItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        sale.setTotalAmount(totalAmount);

        // 7️⃣ Save sale and items
        Sale savedSale = saleRepository.save(sale);
        saleItemRepository.saveAll(saleItems);

        log.info("Sale created successfully: saleId={}, totalAmount={}",
                savedSale.getId(), totalAmount);

        return savedSale;
    }
}