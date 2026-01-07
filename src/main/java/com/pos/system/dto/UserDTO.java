package com.pos.system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Long roleId;
    private Long branchId;
}