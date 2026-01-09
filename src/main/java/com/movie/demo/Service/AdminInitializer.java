package com.movie.demo.Service;



import com.movie.demo.Entity.Role;
import com.movie.demo.Entity.Userinfo;
import com.movie.demo.Repository.UserinfoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserinfoRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin() {

        String adminEmail = "admin@movie.com";

        if (!userRepository.existsByEmail(adminEmail)) {
            Userinfo admin = new Userinfo();
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            System.out.println(" Admin user created");
        }
    }
}

