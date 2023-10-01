package com.land.sismap.excecao;

public class CartaoJaExisteException extends RuntimeException {

    public CartaoJaExisteException() {
        super();
    }

    public CartaoJaExisteException(String message) {
        super(message);
    }

    public CartaoJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartaoJaExisteException(Throwable cause) {
        super(cause);
    }
}
