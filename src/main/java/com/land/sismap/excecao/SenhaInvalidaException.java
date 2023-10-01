package com.land.sismap.excecao;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException() {
        super("Senha inválida");
    }

    public SenhaInvalidaException(String message) {
        super(message);
    }

    public SenhaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SenhaInvalidaException(Throwable cause) {
        super(cause);
    }
}