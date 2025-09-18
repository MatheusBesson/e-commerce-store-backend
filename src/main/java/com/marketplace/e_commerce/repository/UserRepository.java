package com.marketplace.e_commerce.repository;

import com.marketplace.e_commerce.DTO.UserRequestDTO;
import com.marketplace.e_commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
