package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface EmpleadoDAO extends PersonaDAO {
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
