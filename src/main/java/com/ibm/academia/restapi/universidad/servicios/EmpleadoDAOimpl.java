package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepositorio;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepositorio;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoDAOimpl extends PersonaDAOImpl implements EmpleadoDAO {

    EmpleadoDAOimpl(@Qualifier("repositorioEmpleado") PersonaRepositorio repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepositorio) repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

}
