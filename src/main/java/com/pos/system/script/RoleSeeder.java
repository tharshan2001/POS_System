package com.pos.system.script;


import com.pos.system.entity.people.Role;
import com.pos.system.repository.people.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedRoles();
    }

    private void seedRoles() {
        createRoleIfNotExists("CASHIER", "Handles sales transactions");
        createRoleIfNotExists("SHOP_MANAGER", "Manages the shop operations");
        createRoleIfNotExists("WAREHOUSE_MANAGER", "Manages inventory and stock");
        createRoleIfNotExists("WAREHOUSE_STAFF", "Supports warehouse operations");
        createRoleIfNotExists("ADMIN", "Administers the system");
    }

    private void createRoleIfNotExists(String name, String description) {
        roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role(name, description);
            return roleRepository.save(role);
        });
    }
}