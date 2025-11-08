package com.marketplace.e_commerce.controller;


import com.marketplace.e_commerce.DTO.ProductRequestDTO;
import com.marketplace.e_commerce.DTO.ProductResponseDTO;
import com.marketplace.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> postProduct(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO response = productService.postProduct(dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductById(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable Long id) {
        ProductResponseDTO response = productService.updateProduct(productRequestDTO, id);

        return ResponseEntity.ok(response);
    }

    // delete method returning dto (watch service)
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProductById(@PathVariable Long id) {
        ProductResponseDTO response = productService.deleteProductById(id);

        return ResponseEntity.ok(response);
    }
}
