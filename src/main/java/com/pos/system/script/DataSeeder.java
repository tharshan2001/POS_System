package com.pos.system.script;

import com.pos.system.entity.people.Role;
import com.pos.system.entity.people.User;
import com.pos.system.repository.people.RoleRepository;
import com.pos.system.repository.people.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository roleRepository,
                      UserRepository userRepository,
                      BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Seed Super Admin Role
        Role superAdmin = roleRepository.findByName("SUPER_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("SUPER_ADMIN", "Has full access")));

        // Seed Super Admin User
        if (!userRepository.existsByUsername("superadmin")) {
            User admin = new User();
            admin.setUsername("superadmin");
            admin.setPasswordHash(passwordEncoder.encode("admin123")); // hashed password
            admin.setFullName("SUPER_ADMIN");
            admin.setEmail("admin@example.com");
            admin.setRole(superAdmin);
            admin.setCreatedAt(LocalDateTime.now());
            admin.setBranch(null); // null for super admin

            userRepository.save(admin);
            System.out.println("Super admin user seeded!");
        }
    }
}