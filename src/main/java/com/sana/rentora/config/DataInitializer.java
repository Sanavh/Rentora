package com.sana.rentora.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sana.rentora.entity.User;
import com.sana.rentora.enums.RoleEnum;
import com.sana.rentora.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(RoleEnum.ADMIN);

            userRepository.save(admin);

            System.out.println("Admin user created");
        }
    }
}