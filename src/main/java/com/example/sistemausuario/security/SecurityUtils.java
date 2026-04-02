package com.example.sistemausuario.security;

import com.example.sistemausuario.domain.Usuario;
import com.example.sistemausuario.exception.TiposErros;
import com.example.sistemausuario.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SecurityUtils {

    private final UsuarioRepository usuarioRepository;

    public SecurityUtils(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario usuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                Objects.equals(authentication.getPrincipal(), "anonymousUser")) {
            throw new TiposErros.UsuarioNaoAutenticadoException("Usuário não autenticado");
        }

        String email = authentication.getName();

        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new TiposErros.RecursoNaoEncontradoException("Usuário não encontrado"));
    }
}
