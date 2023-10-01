package com.land.sismap.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CartaoInexistenteException extends RuntimeException {

    public CartaoInexistenteException() {
        super("Cartão inexistente");
    }

    public CartaoInexistenteException(String message) {
        super(message);
    }

    public CartaoInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartaoInexistenteException(Throwable cause) {
        super(cause);
    }
}
