package com.marketplace.e_commerce.service;

import com.marketplace.e_commerce.DTO.UserRequestDTO;
import com.marketplace.e_commerce.DTO.UserResponseDTO;
import com.marketplace.e_commerce.model.User;
import com.marketplace.e_commerce.repository.UserRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponseDTO register(UserRequestDTO dto) {
        if (repository.findByUsername(dto.username()).isPresent()) {
            throw new RuntimeException("This username is already in use.");
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());

        User saved = repository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getUsername()
        );
    }

    public List<UserResponseDTO> findAll() {
        List<User> users = repository.findAll();

        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername()
                ))
                .toList();
    }

    public UserResponseDTO findByUsername(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        return new UserResponseDTO(user);
    }
}
