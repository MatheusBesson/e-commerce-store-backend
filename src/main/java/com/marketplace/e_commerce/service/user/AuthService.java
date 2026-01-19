package com.marketplace.e_commerce.service.user;


import com.marketplace.e_commerce.DTO.LoginRequestDTO;
import com.marketplace.e_commerce.DTO.LoginResponseDTO;
import com.marketplace.e_commerce.infra.security.service.JwtService;
import com.marketplace.e_commerce.model.roles.User;
import com.marketplace.e_commerce.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user =  userRepository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token =  jwtService.generateToken(user.getEmail());

        return new LoginResponseDTO(
                token,
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
    }

}
