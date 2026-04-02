package com.example.sistemausuario.exception;

import java.time.LocalDateTime;

public record RespostaErro(
        String mensagem,
        int status,
        String path,
        LocalDateTime  dataHora) {
}
