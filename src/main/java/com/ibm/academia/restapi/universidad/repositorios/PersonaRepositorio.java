package com.ibm.academia.restapi.universidad.repositorios;

import java.util.Optional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonaRepositorio extends CrudRepository<Persona, Long> {

    @Query("select p from Persona p where p.nombre = ?1 and p.apellido = ?2")
    public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);

    @Query("select p from Persona p where p.dni = ?1")
    public Optional<Persona> buscarPorDniString(String dni);

    @Query("select p from Persona p where p.apellido like %?1%")
    public Iterable<Persona> buscarPersonaPorApellido(String apellido);
}
