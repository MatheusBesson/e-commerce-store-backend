package com.marketplace.e_commerce.controller;

import com.marketplace.e_commerce.DTO.UserRequestDTO;
import com.marketplace.e_commerce.DTO.UserResponseDTO;
import com.marketplace.e_commerce.model.User;
import com.marketplace.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/register") // todo
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDTO> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }


    @PostMapping 
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO dto) {
        UserResponseDTO response = userService.register(dto);
        return ResponseEntity.ok(response);
    }
}
