package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepositorio;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {

    @Autowired
    AlumnoDAOImpl(@Qualifier("repositorioAlumno") PersonaRepositorio repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera) {
        return ((AlumnoRepositorio) repository).buscarAlumnosPorNombreCarrera(nombreCarrera);
    }

}
