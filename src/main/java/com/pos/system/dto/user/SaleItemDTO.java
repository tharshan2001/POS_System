package com.pos.system.dto.user;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleItemDTO {
    private Long productId;
    private int quantity;
}

