package com.marketplace.e_commerce.service.product;

import com.marketplace.e_commerce.DTO.OrderResponseDTO;
import com.marketplace.e_commerce.DTO.ShoppingCartItemDTO;
import com.marketplace.e_commerce.DTO.ShoppingCartResponseDTO;
import com.marketplace.e_commerce.model.ShoppingCart.CartItem;
import com.marketplace.e_commerce.model.ShoppingCart.Order.Order;
import com.marketplace.e_commerce.model.ShoppingCart.Order.OrderItem;
import com.marketplace.e_commerce.model.ShoppingCart.ShoppingCart;
import com.marketplace.e_commerce.model.product.Product;
import com.marketplace.e_commerce.model.roles.User;
import com.marketplace.e_commerce.repository.product.CartItemRepository;
import com.marketplace.e_commerce.repository.product.OrderRepository;
import com.marketplace.e_commerce.repository.product.ProductRepository;
import com.marketplace.e_commerce.repository.product.ShoppingCartRepository;
import com.marketplace.e_commerce.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;


    public ShoppingCart createNewShoppingCart(Long userId) {
        ShoppingCart newShoppingCart = new ShoppingCart();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        newShoppingCart.setUser(user);

        return shoppingCartRepository.save(newShoppingCart);
    }

    public ShoppingCart getCartByUser(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> createNewShoppingCart(userId)
                );
        return shoppingCart;
    }

    public ShoppingCartResponseDTO getShoppingCart(Long userId) {
        ShoppingCart cart = getCartByUser(userId);

        List<ShoppingCartItemDTO> items = cart.getItems().stream()
                .map(item -> new ShoppingCartItemDTO(
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getPrice(),
                        item.getQuantity()
                )).toList();

        return new ShoppingCartResponseDTO(
                cart.getId(),
                cart.getUser(),
                items
        );
    }

    @Transactional
    public ShoppingCartItemDTO addCartItem(Long userId, ShoppingCartItemDTO itemDTO) {
        ShoppingCart cart = getCartByUser(userId);

        Product product = productRepository.findById(itemDTO.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getProductId().equals(itemDTO.productId())) {
                item.setQuantity(item.getQuantity() + itemDTO.quantity());
                item.setPrice(BigDecimal.valueOf(product.getProductPrice()));
                shoppingCartRepository.save(cart);

                return new ShoppingCartItemDTO(
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getPrice(),
                        item.getQuantity()
                );
            }
        }

        CartItem newItem = new CartItem();
        newItem.setShoppingCart(cart);
        newItem.setProduct(product);
        newItem.setPrice(BigDecimal.valueOf(product.getProductPrice()));
        newItem.setQuantity(itemDTO.quantity());

        cart.getItems().add(newItem);
        shoppingCartRepository.save(cart);

        return new ShoppingCartItemDTO(
                newItem.getProduct().getProductId(),
                newItem.getProduct().getProductName(),
                newItem.getPrice(),
                newItem.getQuantity()
        );
    }


    // -------------------------------------- ON CHANGE DTOs ------------------------------
    @Transactional
    public ShoppingCartResponseDTO removeCartItem(Long userId, Long productId) {
        ShoppingCart cart = getCartByUser(userId);

        cart.getItems().removeIf(item -> item.getProduct().getProductId().equals(productId));

        shoppingCartRepository.save(cart);

        List<ShoppingCartItemDTO> items = cart.getItems().stream()
                .map(item -> new ShoppingCartItemDTO(
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getPrice(),
                        item.getQuantity()
                )).toList();

        return new ShoppingCartResponseDTO(
                cart.getId(),
                cart.getUser(),
                items
        );
    }

    @Transactional
    public ShoppingCartResponseDTO clearShoppingCart(Long userId) {
        ShoppingCart cart = getCartByUser(userId);

        cart.getItems().clear();

        shoppingCartRepository.save(cart);

        List<ShoppingCartItemDTO> items = cart.getItems().stream()
                .map(item -> new ShoppingCartItemDTO(
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getPrice(),
                        item.getQuantity()
                )).toList();

        return new ShoppingCartResponseDTO(
                cart.getId(),
                cart.getUser(),
                items
        );
    }


}

