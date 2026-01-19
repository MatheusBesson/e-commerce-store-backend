package com.marketplace.e_commerce.DTO;

import com.marketplace.e_commerce.model.ShoppingCart.Order.OrderItem;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        String productName,
        int quantity,
        BigDecimal priceAtPurchase
) {
    public OrderItemDTO(OrderItem item) {
        this(
                item.getProduct().getProductId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                item.getPriceAtPurchase()
        );
    }
}
