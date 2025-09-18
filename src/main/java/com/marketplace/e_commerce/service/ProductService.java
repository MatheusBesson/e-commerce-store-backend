package com.marketplace.e_commerce.service;

import com.marketplace.e_commerce.DTO.ProductRequestDTO;
import com.marketplace.e_commerce.DTO.ProductResponseDTO;
import com.marketplace.e_commerce.model.product.Product;
import com.marketplace.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO register(ProductRequestDTO productRequestDTO) {

        Product product = new Product();

        product.setProductName(productRequestDTO.productName());
        product.setProductPrice(productRequestDTO.productPrice());
        product.setStockQuantity(productRequestDTO.stockQuantity());
        product.setProductImageUrl(productRequestDTO.productImageUrl());

        Product saved = productRepository.save(product);

        return new ProductResponseDTO(
                saved.getId(),
                saved.getProductName(),
                saved.getProductPrice(),
                saved.getStockQuantity(),
                saved.getProductImageUrl()
        );
    }

        public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getStockQuantity(),
                        product.getProductImageUrl()
                ))
                .toList();
    }



}
