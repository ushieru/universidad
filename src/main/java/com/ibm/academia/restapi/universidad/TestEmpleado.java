package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoEmpleado;
import com.ibm.academia.restapi.universidad.servicios.EmpleadoDAO;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestEmpleado implements CommandLineRunner {

	@Autowired
	@Qualifier("empleadoDAOimpl")
	private PersonaDAO personaDao;

	@Override
	public void run(String... args) throws Exception {
		var empleado_0 = ((EmpleadoDAO) personaDao).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		System.out.println(empleado_0);
	}
}
