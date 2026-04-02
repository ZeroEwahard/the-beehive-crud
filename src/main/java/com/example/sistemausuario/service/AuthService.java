package com.example.sistemausuario.service;

import com.example.sistemausuario.domain.Usuario;
import com.example.sistemausuario.exception.TiposErros;
import com.example.sistemausuario.repository.UsuarioRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new TiposErros.RecursoNaoEncontradoException("Usuário não encontrado"));

        return new User(usuario.getEmail(),
                usuario.getSenha(),
                usuario.getAuthorities());
    }
}
