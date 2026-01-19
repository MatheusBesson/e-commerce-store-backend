package com.marketplace.e_commerce.DTO;

import com.marketplace.e_commerce.model.roles.User;

import java.util.List;

public record ShoppingCartResponseDTO(
        Long id,
        User user,
        List<ShoppingCartItemDTO> items
) {
}
