package com.example.sistemausuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Login(@Email
                    @NotBlank
                    String email,
                    @NotBlank
                    String senha) {
}
