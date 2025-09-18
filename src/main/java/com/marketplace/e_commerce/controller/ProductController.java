package com.marketplace.e_commerce.controller;


import com.marketplace.e_commerce.DTO.ProductRequestDTO;
import com.marketplace.e_commerce.DTO.ProductResponseDTO;
import com.marketplace.e_commerce.model.product.Product;
import com.marketplace.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<ProductResponseDTO>> getAllUsers() {
        List<ProductResponseDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> register(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO response = productService.register(dto);
        return ResponseEntity.ok(response);
    }
}
