package com.pos.system.impl.core;


import com.pos.system.dto.core.StockTransferRequest;
import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Core.InventoryTransaction;
import com.pos.system.entity.Core.InventoryTransactionItem;
import com.pos.system.entity.Core.Stock;
import com.pos.system.entity.Lookup.TransactionType;
import com.pos.system.entity.people.User;
import com.pos.system.repository.core.*;
import com.pos.system.repository.lookup.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class StockTransferService {

    private final StockRepository stockRepository;
    private final InventoryTransactionRepository transactionRepository;
    private final InventoryTransactionItemRepository transactionItemRepository;
    private final BranchRepository branchRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    @Transactional
    public void transferStock(User currentUser, StockTransferRequest request) {

        // 1️⃣ Source branch from logged-in user
        Branch fromBranch = currentUser.getBranch();

        // 2️⃣ Destination branch from request
        Branch toBranch = branchRepository.findById(request.getToBranchId())
                .orElseThrow(() -> new RuntimeException("Destination branch not found"));

        // 3️⃣ Transaction type
        TransactionType transactionType = transactionTypeRepository.findById(request.getTransactionTypeId())
                .orElseThrow(() -> new RuntimeException("Transaction type not found"));

        // 4️⃣ Create InventoryTransaction
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setFromBranch(fromBranch);
        transaction.setToBranch(toBranch);
        transaction.setTransactionType(transactionType);
        transaction.setUser(currentUser);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setNotes(request.getNotes());
        transactionRepository.save(transaction);

        // 5️⃣ Process each item
        for (StockTransferRequest.TransferItem item : request.getItems()) {

            // Check stock in source branch
            Stock sourceStock = stockRepository.findByBranchIdAndProductId(fromBranch.getId(), item.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            STR."Product not found in source branch: \{item.getProductId()}"
                    ));

            if (sourceStock.getQuantity() < item.getQuantity()) {
                throw new RuntimeException(
                        STR."Insufficient stock in source branch for product: \{item.getProductId()}"
                );
            }

            // Deduct stock from source branch
            sourceStock.setQuantity(sourceStock.getQuantity() - item.getQuantity());
            stockRepository.save(sourceStock);

            // Add stock to destination branch (create stock if missing)
            Stock destStock = stockRepository.findByBranchIdAndProductId(toBranch.getId(), item.getProductId())
                    .orElseGet(() -> {
                        Stock s = new Stock();
                        s.setBranch(toBranch);
                        s.setProduct(sourceStock.getProduct());
                        s.setQuantity(0);
                        return s;
                    });

            destStock.setQuantity(destStock.getQuantity() + item.getQuantity());
            stockRepository.save(destStock);

            // Log InventoryTransactionItem
            InventoryTransactionItem transactionItem = new InventoryTransactionItem();
            transactionItem.setInventoryTransaction(transaction);
            transactionItem.setProduct(sourceStock.getProduct());
            transactionItem.setQuantity(item.getQuantity());
            transactionItemRepository.save(transactionItem);
        }
    }
}