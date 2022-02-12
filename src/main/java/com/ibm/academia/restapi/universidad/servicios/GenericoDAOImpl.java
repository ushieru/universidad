package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public class GenericoDAOImpl<T, R extends CrudRepository<T, Long>> implements GenericoDAO<T> {

    protected final R repository;

    GenericoDAOImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> buscarPorID(Long id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional
    public T guardar(T object) {
        return this.repository.save(object);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<T> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public void eliminarPorID(Long id) {
        repository.deleteById(id);
    }

}
