package com.marketplace.e_commerce.repository.user;

import com.marketplace.e_commerce.DTO.UserResponseDTO;
import com.marketplace.e_commerce.model.roles.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String userEmail);
}
