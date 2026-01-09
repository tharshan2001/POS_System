package com.pos.system.dto.purchase;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PurchaseDTO {
    private String phone;
    private Long branchId;
    private Double totalAmount;
    private LocalDate purchaseDate;
    private Long paymentMethodId;
    private LocalDate dueDate;
    private String invoiceNumber;
    private Long createdBy;
    private List<PurchaseItemDTO> items;
}

