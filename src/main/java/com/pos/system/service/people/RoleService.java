package com.pos.system.service.people;

import com.pos.system.entity.people.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role createRole(Role role);
}
