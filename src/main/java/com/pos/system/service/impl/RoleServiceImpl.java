package com.pos.system.service.impl;

import com.pos.system.entity.people.Role;
import com.pos.system.repository.people.RoleRepository;
import com.pos.system.service.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role createRole(Role role){
        return roleRepository.save(role);
    }
}
