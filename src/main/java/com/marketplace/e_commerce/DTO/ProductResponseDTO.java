package com.marketplace.e_commerce.DTO;

public record ProductResponseDTO(
        Long id,
        String productName,
        double productPrice,
        Long stockQuantity,
        String productImageUrl
) {
}
