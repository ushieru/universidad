package com.ibm.academia.restapi.universidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;
import com.ibm.academia.restapi.universidad.utils.IterableToSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarreraTest {

	@Autowired
	CarreraDAO carreraDAO;

	@Autowired
	ProfesorDAO profesoresDAO;

	@BeforeAll
	public void init() {
		Carrera carrera = new Carrera(null, "Arquitectura", 25, 4, "root");

		Set<Carrera> carreras = new HashSet<Carrera>();
		carreras.add(carrera);

		Direccion direccion = new Direccion("calle", "numero", "codigoPostal", "departamento", "piso", "localidad");
		Profesor profesor = new Profesor(null, "CProfeNombre", "CProfeApellido", "CProfeDni", direccion,
				new BigDecimal(25000.00));
		profesor.setUsuarioCreacion("root");
		profesor.setCarreras(carreras);

		profesoresDAO.guardar(profesor);
	}

	@Test
	@DisplayName("Carrera - findCarrerasByNombreContains")
	void findCarrerasByNombreContains() {
		Set<Carrera> carreras = IterableToSet.parse(carreraDAO.findCarrerasByNombreContains("itec"));

		Integer expectedResult = 1;

		assertEquals(expectedResult, carreras.size());
	}

	@Test
	@DisplayName("Carrera - findCarrerasByNombreContainsIgnoreCase")
	void findCarrerasByNombreContainsIgnoreCase() {
		Set<Carrera> carreras = IterableToSet.parse(carreraDAO.findCarrerasByNombreContainsIgnoreCase("arquite"));

		Integer expectedResult = 1;

		assertEquals(expectedResult, carreras.size());
	}

	@Test
	@DisplayName("Carrera - findCarrerasByCantidadAniosAfter")
	void findCarrerasByCantidadAniosAfter() {
		Set<Carrera> carreras = IterableToSet.parse(carreraDAO.findCarrerasByCantidadAniosAfter(3));

		Integer expectedResult = 1;

		assertEquals(expectedResult, carreras.size());
	}

	@Test
	@DisplayName("Carrera - buscarCarrerasPorProfesorNombreYApellido")
	void buscarCarrerasPorProfesorNombreYApellido() {
		Set<Carrera> carreras = IterableToSet
				.parse(carreraDAO.buscarCarrerasPorProfesorNombreYApellido("CProfeNombre", "CProfeApellido"));

		Integer expectedResult = 1;

		assertEquals(expectedResult, carreras.size());
	}

}
