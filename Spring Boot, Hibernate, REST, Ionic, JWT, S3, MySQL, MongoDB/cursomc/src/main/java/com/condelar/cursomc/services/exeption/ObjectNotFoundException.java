package com.condelar.cursomc.services.exeption;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String s) {
        super(s);
    }

    public ObjectNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }
}
