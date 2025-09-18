package com.marketplace.e_commerce.DTO;

public record ProductRequestDTO(
        String productName,
        double productPrice,
        Long stockQuantity,
        String productImageUrl
) {
}
