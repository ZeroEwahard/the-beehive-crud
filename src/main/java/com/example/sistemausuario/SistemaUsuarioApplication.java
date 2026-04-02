package com.example.sistemausuario;

import com.example.sistemausuario.domain.Role;
import com.example.sistemausuario.domain.Usuario;
import com.example.sistemausuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SistemaUsuarioApplication {

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.name}")
    private String adminName;

    public static void main(String[] args) {
        SpringApplication.run(SistemaUsuarioApplication.class, args);
    }

    @Bean
    CommandLineRunner criarAdminInicial(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (!usuarioRepository.existsByRole(Role.ROLE_ADMIN)) {

                Usuario admin = new Usuario(
                        adminName,
                        adminEmail,
                        encoder.encode(adminPassword),
                        Role.ROLE_ADMIN
                );
                usuarioRepository.save(admin);
            }
        };
    }
}
