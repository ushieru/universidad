package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumno")
public interface AlumnoRepositorio extends PersonaRepositorio {

    @Query("select a from Alumno a join fetch a.carrera c where c.nombre = ?1")
    public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera);
}
