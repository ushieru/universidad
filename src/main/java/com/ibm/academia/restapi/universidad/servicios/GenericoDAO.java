package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

public interface GenericoDAO<T> {
    public Optional<T> buscarPorID(Long id);

    public T guardar(T object);

    public Iterable<T> buscarTodos();

    public void eliminarPorID(Long id);
}
