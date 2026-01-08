package com.pos.system.controller.sale;

import com.pos.system.dto.user.SaleRequestDTO;
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
import com.pos.system.security.ValidateAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

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
    private final ValidateAdmin validateAdmin;

    public SaleController(SaleRepository saleRepository,
                          SaleItemRepository saleItemRepository,
                          StockRepository stockRepository,
                          CustomerRepository customerRepository,
                          FindCurrentUser currentUser,
                          ValidateAdmin validateAdmin) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.stockRepository = stockRepository;
        this.customerRepository = customerRepository;
        this.currentUser = currentUser;
        this.validateAdmin = validateAdmin;
    }

    // Create sale
    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('CASHIER','SHOP_MANAGER','ADMIN','SUPER_ADMIN')")
    public Sale createSale(@RequestBody SaleRequestDTO request) {

        User user = currentUser.getCurrentUser();

        log.info("Creating sale for user: id={}, username={}", user.getId(), user.getUsername());

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + request.getCustomerId()));

        Sale sale = new Sale();
        sale.setUser(user);
        sale.setCustomer(customer);
        sale.setBranch(user.getBranch());
        sale.setSaleDate(LocalDateTime.now());

        // Map SaleItems
        List<SaleItem> saleItems = request.getItems().stream().map(itemDTO -> {

            Stock stock = stockRepository.findByBranchIdAndProductId(
                            user.getBranch().getId(),
                            itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found in stock: " + itemDTO.getProductId()));

            if (stock.getQuantity() < itemDTO.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + itemDTO.getProductId());
            }

            stock.setQuantity(stock.getQuantity() - itemDTO.getQuantity());
            stockRepository.save(stock);

            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(stock.getProduct());
            saleItem.setQuantity(itemDTO.getQuantity());
            saleItem.setPrice(stock.getProduct().getPrice());
            saleItem.setSale(sale);

            return saleItem;
        }).collect(Collectors.toList());

        double totalAmount = saleItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        sale.setTotalAmount(totalAmount);

        Sale savedSale = saleRepository.save(sale);
        saleItemRepository.saveAll(saleItems);

        log.info("Sale created successfully: saleId={}, totalAmount={}", savedSale.getId(), totalAmount);
        return savedSale;
    }
}