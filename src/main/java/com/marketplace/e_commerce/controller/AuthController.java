package com.marketplace.e_commerce.controller;

import com.marketplace.e_commerce.DTO.LoginRequestDTO;
import com.marketplace.e_commerce.DTO.LoginResponseDTO;
import com.marketplace.e_commerce.DTO.UserRequestDTO;
import com.marketplace.e_commerce.DTO.UserResponseDTO;
import com.marketplace.e_commerce.service.user.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        LoginResponseDTO loginResponseDTO = authService.login(dto);

        return ResponseEntity.ok(loginResponseDTO);
    }

}
