package com.pos.system.impl;

import com.pos.system.dto.user.UserDTO;
import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.people.Role;
import com.pos.system.entity.people.User;
import com.pos.system.exception.ResourceNotFoundException;
import com.pos.system.repository.core.BranchRepository;
import com.pos.system.repository.people.RoleRepository;
import com.pos.system.repository.people.UserRepository;
import com.pos.system.security.PasswordUtil;
import com.pos.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BranchRepository branchRepository;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BranchRepository branchRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public User createUser(UserDTO userDTO) {

        // 1️⃣ Basic validation
        if (userDTO.getUsername() == null ||
                userDTO.getEmail() == null ||
                userDTO.getPassword() == null) {
            throw new IllegalArgumentException("Username, email, and password cannot be null");
        }

        // 2️⃣ Unique checks
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // 3️⃣ Fetch Role (MANDATORY)
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with id: " + userDTO.getRoleId())
                );

        // 4️⃣ Fetch Branch (OPTIONAL – depends on role)
        Branch branch = null;
        if (userDTO.getBranchId() != null) {
            branch = branchRepository.findById(userDTO.getBranchId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Branch not found with id: " + userDTO.getBranchId())
                    );
        }

        // 5️⃣ Map DTO → Entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPasswordHash(PasswordUtil.encode(userDTO.getPassword()));
        user.setRole(role);
        user.setBranch(branch);

        // 6️⃣ Save
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id)
                );
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
        user.setPhoneNumber(data.getPhoneNumber());

        // ⚠️ Only update password if provided
        if (data.getPasswordHash() != null) {
            user.setPasswordHash(data.getPasswordHash());
        }

        // Update role & branch explicitly
        user.setRole(data.getRole());
        user.setBranch(data.getBranch());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}