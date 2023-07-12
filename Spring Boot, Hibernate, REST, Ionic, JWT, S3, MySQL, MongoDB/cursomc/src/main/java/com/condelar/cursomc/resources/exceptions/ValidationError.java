package com.condelar.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String messagem) {
        this.erros.add(new FieldMessage(fieldName, messagem));
    }
}
