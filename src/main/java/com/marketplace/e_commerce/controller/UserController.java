package com.marketplace.e_commerce.controller;

import com.marketplace.e_commerce.DTO.UpdateAvatarRequestDTO;
import com.marketplace.e_commerce.DTO.UserRequestDTO;
import com.marketplace.e_commerce.DTO.UserResponseDTO;
import com.marketplace.e_commerce.repository.user.UserRepository;
import com.marketplace.e_commerce.service.user.UserService;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000") // change later, all requisitions permission
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        UserResponseDTO responseDTO = userService.createUser(userDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> responseDTO = userService.getUsers();

        return ResponseEntity.ok(responseDTO);
    }

    // on going!!!!!!!!!-----------
    @PatchMapping("/me/avatar")
    public ResponseEntity<UserResponseDTO> updateAvatar(@RequestBody UpdateAvatarRequestDTO dto, Authentication authentication) {
        UserResponseDTO userResponseDTO = userService.updateAvatar(dto, authentication);

        return ResponseEntity.ok(userResponseDTO);
    }


}
