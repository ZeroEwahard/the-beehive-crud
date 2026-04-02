package com.example.sistemausuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastrarUsuario(@NotBlank
                                    String nome,
                                    @Email
                                    @NotBlank
                                    String email,
                                    @NotBlank
                                    @Size(min = 12, max = 128)
                                    String senha) {
}
