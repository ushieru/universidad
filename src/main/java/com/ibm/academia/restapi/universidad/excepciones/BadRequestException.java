package com.ibm.academia.restapi.universidad.excepciones;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}