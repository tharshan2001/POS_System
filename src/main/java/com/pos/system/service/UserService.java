package com.pos.system.service;

import com.pos.system.dto.UserDTO;
import com.pos.system.entity.people.User;
import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTOr);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}