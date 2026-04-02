package com.example.sistemausuario.repository;

import com.example.sistemausuario.domain.Role;
import com.example.sistemausuario.domain.Usuario;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(@Email String email);

    boolean existsByRole(Role role);

}
