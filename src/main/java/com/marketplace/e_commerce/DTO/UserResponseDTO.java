package com.marketplace.e_commerce.DTO;

import com.marketplace.e_commerce.model.User;

public record UserResponseDTO(
        Long id,
        String name
) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getUsername());
    }
}