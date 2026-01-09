package com.pos.system.dto.purchase;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseItemDTO {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
}
