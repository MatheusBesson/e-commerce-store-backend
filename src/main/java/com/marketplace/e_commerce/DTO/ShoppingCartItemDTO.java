package com.marketplace.e_commerce.DTO;

import java.math.BigDecimal;

public record ShoppingCartItemDTO(
        Long productId,
        String productName,
        BigDecimal price,
        int quantity
) {
}
