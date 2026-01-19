package com.marketplace.e_commerce.DTO;

import com.marketplace.e_commerce.model.ShoppingCart.Order.Order;
import com.marketplace.e_commerce.model.ShoppingCart.Order.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long orderId,
        Long userId,
        BigDecimal total,
        LocalDateTime createdAt,
        String status,

        List<OrderItemDTO> items


) {

    public OrderResponseDTO(Order order) {
       this(
            order.getId(),
            order.getUser().getId(),
            order.getTotal(),
            order.getCreatedAt(),
            order.getStatus(),
            order.getItems().stream()
                    .map(OrderItemDTO::new)
                    .toList()
        );
    }
}
