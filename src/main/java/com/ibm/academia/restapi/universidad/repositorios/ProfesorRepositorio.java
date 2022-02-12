package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioProfesor")
public interface ProfesorRepositorio extends PersonaRepositorio {

    @Query("SELECT p FROM Profesor p JOIN FETCH p.carreras c WHERE c.nombre = ?1")
    public Iterable<Persona> findProfesoresByCarrera(String carrera);
}
