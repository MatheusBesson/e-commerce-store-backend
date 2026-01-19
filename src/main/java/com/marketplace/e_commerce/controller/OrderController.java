package com.marketplace.e_commerce.controller;

import com.marketplace.e_commerce.DTO.OrderResponseDTO;
import com.marketplace.e_commerce.service.product.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;


    @GetMapping("/{userId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long userId) {
        OrderResponseDTO orderResponseDTO = orderService.getOrder(userId);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Long userId) {
        OrderResponseDTO orderResponseDTO = orderService.checkout(userId);

        return ResponseEntity.ok(orderResponseDTO);
    }

    /*
    @DeleteMapping("/{userId}")
    public ResponseEntity<OrderResponseDTO> deleteOrder (@PathVariable Long userId, @PathVariable Long orderId) {



    } */


}
