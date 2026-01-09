package com.pos.system.impl.purchases;

import com.pos.system.dto.purchase.PurchaseDTO;
import com.pos.system.dto.purchase.PurchaseItemDTO;
import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Core.Product;
import com.pos.system.entity.Core.Stock;
import com.pos.system.entity.Lookup.PaymentMethod;
import com.pos.system.entity.Purchases.Purchase;
import com.pos.system.entity.Purchases.PurchaseItem;
import com.pos.system.entity.people.Supplier;
import com.pos.system.entity.people.User;
import com.pos.system.exception.ResourceNotFoundException;
import com.pos.system.repository.core.BranchRepository;
import com.pos.system.repository.core.ProductRepository;
import com.pos.system.repository.core.StockRepository;
import com.pos.system.repository.purchase.PurchaseItemRepository;
import com.pos.system.repository.purchase.PurchaseRepository;
import com.pos.system.repository.people.SupplierRepository;
import com.pos.system.repository.lookup.PaymentMethodRepository;
import com.pos.system.security.FindCurrentUser;
import com.pos.system.service.purchases.PurchaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final FindCurrentUser findCurrentUser;
    private final SupplierRepository supplierRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               PurchaseItemRepository purchaseItemRepository,
                               BranchRepository branchRepository,
                               ProductRepository productRepository,
                               StockRepository stockRepository,
                               FindCurrentUser findCurrentUser,
                               SupplierRepository supplierRepository,
                               PaymentMethodRepository paymentMethodRepository) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseItemRepository = purchaseItemRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.findCurrentUser = findCurrentUser;
        this.supplierRepository = supplierRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    // --- Create purchase from DTO ---
    @Override
    public Purchase createPurchase(PurchaseDTO dto) {

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));

        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        PaymentMethod paymentMethod = paymentMethodRepository.findById(dto.getPaymentMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found"));

        User currentUser = findCurrentUser.getCurrentUser();

        Purchase purchase = new Purchase();
        purchase.setBranch(branch);
        purchase.setSupplier(supplier);
        purchase.setPaymentMethod(paymentMethod);
        purchase.setTotalAmount(dto.getTotalAmount());
        purchase.setPurchaseDate(dto.getPurchaseDate());
        purchase.setDueDate(LocalDate.from(dto.getDueDate()));
        purchase.setInvoiceNumber(dto.getInvoiceNumber());
        purchase.setCreatedBy(currentUser);

        purchase = purchaseRepository.save(purchase);

        List<PurchaseItem> items = new ArrayList<>();
        for (PurchaseItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            PurchaseItem item = new PurchaseItem();
            item.setPurchase(purchase);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            purchaseItemRepository.save(item);
            items.add(item);

            // Update or create stock
            Stock stock = stockRepository.findByBranchAndProduct(branch, product)
                    .orElseGet(() -> {
                        Stock newStock = new Stock();
                        newStock.setBranch(branch);
                        newStock.setProduct(product);
                        newStock.setQuantity(0);
                        return newStock;
                    });

            stock.setQuantity(stock.getQuantity() + itemDTO.getQuantity());
            stock.setUpdatedAt(LocalDateTime.now());
            stockRepository.save(stock);
        }

        return purchase;
    }

    // --- Save Purchase entity directly ---
    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    // --- Find purchase by ID ---
    @Override
    public Optional<Purchase> findById(Long id) {
        return purchaseRepository.findById(id);
    }

    // --- Find by invoice number ---
    @Override
    public Optional<Purchase> findByInvoiceNumber(String invoiceNumber) {
        return purchaseRepository.findByInvoiceNumber(invoiceNumber);
    }

    // --- Find by supplier ---
    @Override
    public List<Purchase> findBySupplierId(Long supplierId) {
        return purchaseRepository.findBySupplier_Id(supplierId);
    }

    // --- Find by branch ---
    @Override
    public List<Purchase> findByBranchId(Long branchId) {
        return purchaseRepository.findByBranch_Id(branchId);
    }

    // --- Delete by ID ---
    @Override
    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }

    // --- Get by ID (throws exception if not found) ---
    @Override
    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));
    }

    // --- Get all purchases ---
    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}