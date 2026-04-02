package com.example.sistemausuario.exception;

public class TiposErros {

    public static class UsuarioNaoAutenticadoException extends RuntimeException {
        public  UsuarioNaoAutenticadoException(String mensagem) {
            super(mensagem);
        }
    }

    public static class EmailCadastradoException extends RuntimeException {
        public EmailCadastradoException(String mensagem) {
            super(mensagem);
        }
    }

    public static class SemPermissaoException extends RuntimeException {
        public SemPermissaoException(String mensagem) {
            super(mensagem);
        }
    }

    public static class RecursoNaoEncontradoException extends RuntimeException {
        public RecursoNaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }

    public static class CredenciaisInvalidaException extends RuntimeException {
        public CredenciaisInvalidaException(String mensagem) {
            super(mensagem);
        }
    }
}
