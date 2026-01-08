package com.pos.system.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class SaleRequestDTO {
    private Long customerId;
    private Long paymentMethodId;
    private List<SaleItemDTO> items;
}
