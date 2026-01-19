package com.marketplace.e_commerce.service.product;

import com.marketplace.e_commerce.DTO.OrderItemDTO;
import com.marketplace.e_commerce.DTO.OrderResponseDTO;
import com.marketplace.e_commerce.model.ShoppingCart.CartItem;
import com.marketplace.e_commerce.model.ShoppingCart.Order.Order;
import com.marketplace.e_commerce.model.ShoppingCart.Order.OrderItem;
import com.marketplace.e_commerce.model.ShoppingCart.ShoppingCart;
import com.marketplace.e_commerce.repository.product.OrderRepository;
import com.marketplace.e_commerce.repository.product.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    final private OrderRepository orderRepository;

    @Autowired
    final private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    final private ShoppingCartService shoppingCartService;


    public OrderResponseDTO getOrder(Long userId) {
        Order order = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has no orders"));

        List<OrderItemDTO> orderItems = order.getItems().stream().map(item -> new OrderItemDTO(
                item.getProduct().getProductId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                item.getPriceAtPurchase()
        )).toList();

        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getTotal(),
                order.getCreatedAt(),
                order.getStatus(),
                orderItems
        );
    }


    public BigDecimal getTotal(Long userId) {
        ShoppingCart cart = shoppingCartService.getCartByUser(userId);

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart.getItems()) {
            BigDecimal price = BigDecimal.valueOf(item.getProduct().getProductPrice());
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());

            total = total.add(price.multiply(quantity));
        }
        return total;
    }

    @Transactional
    public OrderResponseDTO checkout(Long userId) {
        ShoppingCart cart = shoppingCartService.getCartByUser(userId);

        if (cart.getItems().isEmpty()) {
            throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has no cart items");
        }

        BigDecimal total = getTotal(userId);
        List<OrderItem> orderItems = new ArrayList<>();

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING_PAYMENT");
        order.setTotal(total);

        for (CartItem item : cart.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPriceAtPurchase(
                    BigDecimal.valueOf(item.getProduct().getProductPrice()));

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);


        orderRepository.save(order);

        //cleaning cart
        if (order.getStatus().equalsIgnoreCase("PAID")) {
            order.setStatus("SHIPPED");
            shoppingCartService.clearShoppingCart(userId);
            shoppingCartRepository.save(cart);
        }
        return new OrderResponseDTO(order);
    }


}
