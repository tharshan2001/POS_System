package com.pos.system.impl;

import com.pos.system.dto.user.UserDTO;
import com.pos.system.entity.people.User;
import com.pos.system.exception.ResourceNotFoundException;
import com.pos.system.repository.people.UserRepository;
import com.pos.system.security.PasswordUtil;
import com.pos.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repo) {
        this.userRepository = repo;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getEmail() == null || userDTO.getPassword() == null) {
            throw new IllegalArgumentException("Username, email, and password cannot be null");
        }

        // Optional: check for existing username/email
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPasswordHash(PasswordUtil.encode(userDTO.getPassword()));

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            // Log the actual exception
            e.printStackTrace();
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User data) {
        User user = getUserById(id);
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setFullName(data.getFullName());
        user.setPasswordHash(data.getPasswordHash());
        user.setRole(data.getRole());
        user.setBranch(data.getBranch());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}