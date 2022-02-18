package com.ibm.academia.restapi.universidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Set;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.EmpleadoDAO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmpleadoTest {

	@Autowired
	EmpleadoDAO empleadoDAO;

	@BeforeAll
	public void init() {
		Direccion direccion = new Direccion("calle", "numero", "codigoPostal", "departamento", "piso", "localidad");
		Pabellon pabellon_0 = new Pabellon(null, 10.0, "EmplPabellon_0", direccion, "root");
		Pabellon pabellon_1 = new Pabellon(null, 10.0, "EmplPabellon_1", direccion, "root");
		Pabellon pabellon_2 = new Pabellon(null, 10.0, "EmplPabellon_2", direccion, "root");

		Empleado empleado_0 = new Empleado(null, "empleadoNombre_0", "empleadoApellido_0", "emplDni_0", direccion,
				new BigDecimal(25000.00),
				TipoEmpleado.ADMINISTRATIVO);
		empleado_0.setUsuarioCreacion("root");
		empleado_0.setPabellon(pabellon_0);
		Empleado empleado_1 = new Empleado(null, "empleadoNombre_1", "empleadoApellido_1", "emplDni_1", direccion,
				new BigDecimal(25000.00),
				TipoEmpleado.ADMINISTRATIVO);
		empleado_1.setUsuarioCreacion("root");
		empleado_1.setPabellon(pabellon_1);
		Empleado empleado_2 = new Empleado(null, "empleadoNombre_2", "empleadoApellido_2", "emplDni_2", direccion,
				new BigDecimal(25000.00),
				TipoEmpleado.MANTENIMIENTO);
		empleado_2.setUsuarioCreacion("root");
		empleado_2.setPabellon(pabellon_2);

		empleadoDAO.guardar(empleado_0);
		empleadoDAO.guardar(empleado_1);
		empleadoDAO.guardar(empleado_2);
	}

	@Test
	@DisplayName("Empleado - findEmpleadoByTipoEmpleado")
	void findEmpleadoByTipoEmpleado() {
		Set<Persona> empleados = (Set<Persona>) empleadoDAO.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);

		Integer expectedResult = 2;

		assertEquals(expectedResult, empleados.size());
	}

}
