package com.example.sistemausuario.dto;

import com.example.sistemausuario.domain.Usuario;

public record DadosListagemUsuarios(Long id, String nome, String email, String senha) {

    public DadosListagemUsuarios(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
