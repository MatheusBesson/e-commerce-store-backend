package com.marketplace.e_commerce.repository.product;

import com.marketplace.e_commerce.model.ShoppingCart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
