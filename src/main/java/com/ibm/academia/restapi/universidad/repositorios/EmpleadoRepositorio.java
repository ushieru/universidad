package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioEmpleado")
public interface EmpleadoRepositorio extends PersonaRepositorio {
    
    @Query("SELECT e FROM Empleado e WHERE e.tipoEmpleado = ?1")
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
