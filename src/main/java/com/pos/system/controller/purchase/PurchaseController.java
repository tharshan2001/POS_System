package com.pos.system.controller.purchase;

import com.pos.system.dto.purchase.PurchaseDTO;
import com.pos.system.entity.Purchases.Purchase;
import com.pos.system.service.purchases.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * CREATE PURCHASE
     * - Saves purchase
     * - Saves purchase items
     * - Updates stock automatically
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.createPurchase(purchaseDTO);
    }

    /**
     * GET ALL PURCHASES
     */
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    /**
     * GET PURCHASE BY ID
     */
    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    /**
     * GET PURCHASE BY INVOICE NUMBER
     */
    @GetMapping("/invoice/{invoiceNumber}")
    public Purchase getByInvoiceNumber(@PathVariable String invoiceNumber) {
        return purchaseService.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
    }

    /**
     * GET PURCHASES BY SUPPLIER
     */
    @GetMapping("/supplier/{supplierId}")
    public List<Purchase> getBySupplier(@PathVariable Long supplierId) {
        return purchaseService.findBySupplierId(supplierId);
    }

    /**
     * GET PURCHASES BY BRANCH
     */
    @GetMapping("/branch/{branchId}")
    public List<Purchase> getByBranch(@PathVariable Long branchId) {
        return purchaseService.findByBranchId(branchId);
    }

    /**
     * DELETE PURCHASE
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchase(@PathVariable Long id) {
        purchaseService.deleteById(id);
    }
}