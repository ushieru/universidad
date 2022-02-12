package com.ibm.academia.restapi.universidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.AlumnoDAO;
import com.ibm.academia.restapi.universidad.utils.IterableToSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlumnoTest {

	String nombreCarrera = "Ingenieria en Computacion";

	@Autowired
	AlumnoDAO alumnoDAO;

	@BeforeAll
	public void init() {
		Carrera carrera = new Carrera(null, nombreCarrera, 25, 4, "root");
		Direccion direccion = new Direccion("calle", "numero", "codigoPostal", "departamento", "piso", "localidad");
		Alumno alumno = new Alumno(null, "alumnoNombre", "alumnoApellido", "alumnoDni", direccion);
		alumno.setUsuarioCreacion("root");
		alumno.setCarrera(carrera);

		alumnoDAO.guardar(alumno);
	}

	@Test
	@DisplayName("Alumno - buscarAlumnosPorNombreCarrera")
	void alumnoBuscarAlumnosPorNombreCarrera() {
		Set<Persona> alumnos = IterableToSet.parse(alumnoDAO.buscarAlumnosPorNombreCarrera(nombreCarrera));

		Integer expectedResult = 1;

		assertEquals(expectedResult, alumnos.size());
	}

}
