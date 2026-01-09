package com.pos.system.script;

import com.pos.system.service.*;
import com.pos.system.service.Core.CategoryService;
import com.pos.system.service.Lookup.BranchTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class MasterDataSeeder implements CommandLineRunner {

    private final PaymentMethodService paymentMethodService;
    private final StatusService statusService;
    private final TransactionTypeService transactionTypeService;
    private final CategoryService categoryService;
    private final BranchTypeService branchTypeService;

    @Override
    public void run(String... args) {

        // Branch Types
        branchTypeService.createIfNotExists("RETAIL");
        branchTypeService.createIfNotExists("WAREHOUSE");

        // Payment Methods
        paymentMethodService.createIfNotExists("Cash");
        paymentMethodService.createIfNotExists("Card");
        paymentMethodService.createIfNotExists("Credit");
        paymentMethodService.createIfNotExists("Cheque");

        // Statuses
        statusService.createIfNotExists("Active");
        statusService.createIfNotExists("DeActive");
        statusService.createIfNotExists("OutOfStock");

        // Transaction Types
        transactionTypeService.createIfNotExists("Shop2Shop");
        transactionTypeService.createIfNotExists("Warehouse2Shop");
        transactionTypeService.createIfNotExists("Shop2Warehouse");

        // Categories
        categoryService.createIfNotExists("General");
        categoryService.createIfNotExists("Electronics");
    }
}