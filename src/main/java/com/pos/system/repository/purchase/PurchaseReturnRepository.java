package com.pos.system.repository.purchase;

import com.pos.system.entity.Purchases.PurchaseReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseReturnRepository extends JpaRepository<PurchaseReturn, Long> {

    List<PurchaseReturn> findByPurchaseItemId(Long purchaseItemId);
}
