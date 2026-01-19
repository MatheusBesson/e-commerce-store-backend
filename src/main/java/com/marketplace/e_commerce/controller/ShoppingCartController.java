package com.marketplace.e_commerce.controller;

import com.marketplace.e_commerce.DTO.ShoppingCartItemDTO;
import com.marketplace.e_commerce.DTO.ShoppingCartResponseDTO;
import com.marketplace.e_commerce.model.ShoppingCart.ShoppingCart;
import com.marketplace.e_commerce.service.product.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{userId}/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    //FIND CART BY USER ID
    @GetMapping()
    public ResponseEntity<ShoppingCartResponseDTO> getCartByUserId(@PathVariable Long userId) {
        ShoppingCartResponseDTO userShoppingCartDTO = shoppingCartService.getShoppingCart(userId);
        return ResponseEntity.ok(userShoppingCartDTO);
    }

    @PostMapping()
    public ResponseEntity<ShoppingCartItemDTO> addCartItem(
            @PathVariable Long userId,
            @RequestBody ShoppingCartItemDTO request
            ) {
        ShoppingCartItemDTO itemDTO = shoppingCartService.addCartItem(userId, request);

        return ResponseEntity.ok(itemDTO);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ShoppingCartResponseDTO> removeCartItem(@PathVariable Long userId, @PathVariable Long productId) {
        ShoppingCartResponseDTO responseDTO = shoppingCartService.removeCartItem(userId, productId);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<ShoppingCartResponseDTO> clearShoppingCart(@PathVariable Long userId) {
        ShoppingCartResponseDTO responseDTO = shoppingCartService.clearShoppingCart(userId);
        return ResponseEntity.ok(responseDTO);
    }





}
