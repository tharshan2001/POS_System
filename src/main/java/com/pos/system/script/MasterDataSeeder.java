package com.pos.system.script;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.BranchType;
import com.pos.system.service.Core.BranchService;
import com.pos.system.service.Core.CategoryService;
import com.pos.system.service.Lookup.BranchTypeService;
import com.pos.system.service.Lookup.PaymentMethodService;
import com.pos.system.service.Lookup.StatusService;
import com.pos.system.service.Lookup.TransactionTypeService;
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
    private final BranchService branchService;

    @Override
    public void run(String... args) {

        // -------- Branch Types --------
        branchTypeService.createIfNotExists("RETAIL");
        branchTypeService.createIfNotExists("WAREHOUSE");

        // -------- Default Warehouse --------
        BranchType warehouseType = branchTypeService.getByName("WAREHOUSE");

        if (!branchService.existsByType(warehouseType)) {
            Branch warehouse = new Branch();
            warehouse.setName("Main Warehouse");
            warehouse.setType(warehouseType);
            warehouse.setAddress("Default Warehouse Address");

            branchService.createSystemBranch(warehouse);
        }

        // -------- Payment Methods --------
        paymentMethodService.createIfNotExists("Cash");
        paymentMethodService.createIfNotExists("Card");
        paymentMethodService.createIfNotExists("Credit");
        paymentMethodService.createIfNotExists("Cheque");

        // -------- Statuses --------
        statusService.createIfNotExists("Active");
        statusService.createIfNotExists("DeActive");
        statusService.createIfNotExists("OutOfStock");

        // -------- Transaction Types --------
        transactionTypeService.createIfNotExists("Shop2Shop");
        transactionTypeService.createIfNotExists("Warehouse2Shop");
        transactionTypeService.createIfNotExists("Shop2Warehouse");

        // -------- Categories --------
        categoryService.createIfNotExists("General");
        categoryService.createIfNotExists("Electronics");
    }
}