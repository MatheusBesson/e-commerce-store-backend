package com.marketplace.e_commerce.service.user;

import com.marketplace.e_commerce.DTO.UpdateAvatarRequestDTO;
import com.marketplace.e_commerce.DTO.UserRequestDTO;
import com.marketplace.e_commerce.DTO.UserResponseDTO;
import com.marketplace.e_commerce.model.roles.User;
import com.marketplace.e_commerce.repository.user.UserRepository;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setUsername(userRequestDTO.username());
        user.setEmail(userRequestDTO.email());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getAvatarUrl(),
                savedUser.getRoles()
        );

    }

    public List<UserResponseDTO> getUsers() {

        List<UserResponseDTO> usersList = userRepository.findAll().stream().map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getAvatarUrl(),
                        user.getRoles()
                )
        ).toList();

        return usersList;
    }

    public UserResponseDTO updateAvatar(UpdateAvatarRequestDTO dto, Authentication authentication) {
        String email = authentication.getName();
        System.out.println("AUTH NAME >>> " + authentication.getName());

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if(dto.avatarUrl() == null || dto.avatarUrl().isBlank()) {
            throw  new IllegalArgumentException("Avatar URL cannot be empty");
        }

        user.setAvatarUrl(dto.avatarUrl());
        userRepository.save(user);

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAvatarUrl(),
                user.getRoles()
        );
    }

}
