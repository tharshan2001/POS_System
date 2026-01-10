package com.pos.system.dto.core;

import lombok.Data;

import java.util.List;

@Data
public class StockTransferRequest {
    private Long toBranchId;
    private Long transactionTypeId;
    private List<TransferItem> items;
    private String notes;

    @Data
    public static class TransferItem {
        private Long productId;
        private int quantity;
    }
}