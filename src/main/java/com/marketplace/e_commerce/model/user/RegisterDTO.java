package com.marketplace.e_commerce.model.user;


public record RegisterDTO(
        String username,
        String password,
        UserRole role
) {
}
