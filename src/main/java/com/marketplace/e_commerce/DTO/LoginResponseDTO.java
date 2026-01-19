package com.marketplace.e_commerce.DTO;

import java.util.Set;


public record LoginResponseDTO(
        String token,
        String username,
        String email,
        Set<String> roles
) {
}
