package com.marketplace.e_commerce.service.product;

import com.marketplace.e_commerce.DTO.ProductRequestDTO;
import com.marketplace.e_commerce.DTO.ProductResponseDTO;
import com.marketplace.e_commerce.model.product.Product;
import com.marketplace.e_commerce.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO postProduct(ProductRequestDTO productRequestDTO) {

        Product product = new Product();

        product.setProductName(productRequestDTO.productName());
        product.setProductPrice(productRequestDTO.productPrice());
        product.setStockQuantity(productRequestDTO.stockQuantity());
        product.setProductImageUrl(productRequestDTO.productImageUrl());

        Product saved = productRepository.save(product);

        return new ProductResponseDTO(
                saved.getProductId(),
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
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getStockQuantity(),
                        product.getProductImageUrl()
                ))
                .toList();
    }

    // Update method
    public ProductResponseDTO updateProduct( ProductRequestDTO productRequestDTOUpdated, Long id) {
        Product productToUpdate = productRepository.findById(id)
                    .orElseThrow(() -> new NullPointerException("Product not found in database"));

        productToUpdate.setProductName(productRequestDTOUpdated.productName());
        productToUpdate.setProductPrice(productRequestDTOUpdated.productPrice());
        productToUpdate.setProductImageUrl(productToUpdate.getProductImageUrl());
        productToUpdate.setStockQuantity(productToUpdate.getStockQuantity());

        Product saved = productRepository.save(productToUpdate);

        return new ProductResponseDTO(productToUpdate.getProductId(),
                saved.getProductName(),
                saved.getProductPrice(),
                saved.getStockQuantity(),
                saved.getProductImageUrl());
    }



    public ProductResponseDTO deleteProductById(Long id) {
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Product id not found."));

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                productToDelete.getProductId(),
                productToDelete.getProductName(),
                productToDelete.getProductPrice(),
                productToDelete.getStockQuantity(),
                productToDelete.getProductImageUrl()
        );

        productRepository.delete(productToDelete);

        return productResponseDTO;
    }

}
