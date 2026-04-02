package com.example.sistemausuario.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TiposErros.UsuarioNaoAutenticadoException.class)
    public ResponseEntity<RespostaErro> tratarNaoAutenticado(TiposErros.UsuarioNaoAutenticadoException ex, HttpServletRequest request) {
        return buildResponse(ex, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(TiposErros.EmailCadastradoException.class)
    public ResponseEntity<RespostaErro> tratarEmail(TiposErros.EmailCadastradoException ex, HttpServletRequest request) {
        return buildResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(TiposErros.SemPermissaoException.class)
    public ResponseEntity<RespostaErro> tratarPermissao(TiposErros.SemPermissaoException ex, HttpServletRequest request) {
        return buildResponse(ex, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(TiposErros.RecursoNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> tratarNaoEncontrado(TiposErros.RecursoNaoEncontradoException ex, HttpServletRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(TiposErros.CredenciaisInvalidaException.class)
    public ResponseEntity<RespostaErro> tratarCrendenciais(TiposErros.CredenciaisInvalidaException ex, HttpServletRequest request) {
        return buildResponse(ex, HttpStatus.UNAUTHORIZED, request);
    }

    private ResponseEntity<RespostaErro> buildResponse(Exception ex, HttpStatus status, HttpServletRequest request) {
        RespostaErro erro = new RespostaErro(
                ex.getMessage(),
                status.value(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(erro);
    }
}
