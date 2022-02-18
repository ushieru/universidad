package com.ibm.academia.restapi.universidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfesorTest {

	@Autowired
	ProfesorDAO profesorDAO;

	@BeforeAll
	public void init() {
		Carrera carrera = new Carrera(null, "Filosofia", 25, 4, "root");
		Set<Carrera> carreras = new HashSet<Carrera>();
		carreras.add(carrera);

		Direccion direccion = new Direccion("calle", "numero", "codigoPostal", "departamento", "piso", "localidad");
		Profesor profesor = new Profesor(null, "profeNombre", "ProfeApellido", "ProfeDni", direccion,
				new BigDecimal(25000.00));
		profesor.setUsuarioCreacion("root");
		profesor.setCarreras(carreras);

		profesorDAO.guardar(profesor);
	}

	@Test
	@DisplayName("Profesor - findProfesoresByCarrera")
	void findProfesoresByCarrera() {
		Set<Persona> pabellones = (Set<Persona>) profesorDAO.findProfesoresByCarrera("Filosofia");

		Integer expectedResult = 1;

		assertEquals(expectedResult, pabellones.size());
	}

}
