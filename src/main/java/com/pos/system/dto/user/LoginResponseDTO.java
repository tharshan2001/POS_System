package com.pos.system.dto.user;


import com.pos.system.entity.people.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String fullName;
    private String role;
}
