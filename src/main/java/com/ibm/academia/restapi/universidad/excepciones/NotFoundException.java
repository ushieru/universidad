package com.ibm.academia.restapi.universidad.excepciones;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
