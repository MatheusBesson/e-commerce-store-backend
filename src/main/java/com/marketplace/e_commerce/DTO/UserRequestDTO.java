package com.marketplace.e_commerce.DTO;

public record UserRequestDTO(
        String username,
        String email,
        String password
        // List<String> roles;
) {
}
