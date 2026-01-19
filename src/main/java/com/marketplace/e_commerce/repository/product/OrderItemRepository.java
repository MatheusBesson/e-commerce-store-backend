package com.marketplace.e_commerce.repository.product;

import com.marketplace.e_commerce.model.ShoppingCart.Order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
