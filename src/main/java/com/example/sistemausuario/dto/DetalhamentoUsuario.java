package com.example.sistemausuario.dto;


import com.example.sistemausuario.domain.Role;
import com.example.sistemausuario.domain.Usuario;

public record DetalhamentoUsuario(Long id, String nome, String email, Role role) {

    public DetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(),  usuario.getRole());
    }
}
