package com.marketplace.e_commerce.DTO;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String avatarUrl,
        Set<String> roles
) {
}
