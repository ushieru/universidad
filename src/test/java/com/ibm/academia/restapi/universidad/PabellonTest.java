package com.ibm.academia.restapi.universidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PabellonTest {

	@Autowired
	PabellonDAO pabellonDAO;

	@BeforeAll
	public void init() {
		Direccion direccion_0 = new Direccion("calle", "numero", "codigoPostal", "departamento", "piso",
				"PabellonLocalidad");
		Direccion direccion_1 = new Direccion("calle", "numero", "codigoPostal", "departamento", "piso", "localidad2");
		Pabellon pabellon_0 = new Pabellon(null, 10.0, "pabellon_0", direccion_0, "root");
		Pabellon pabellon_1 = new Pabellon(null, 10.0, "pabellon_1", direccion_0, "root");
		Pabellon pabellon_2 = new Pabellon(null, 10.0, "pabellon_1", direccion_1, "root");

		pabellonDAO.guardar(pabellon_0);
		pabellonDAO.guardar(pabellon_1);
		pabellonDAO.guardar(pabellon_2);
	}

	@Test
	@DisplayName("Pabellon - findPabellonesByDireccionLocalidad")
	void findPabellonesByDireccionLocalidad() {
		Set<Pabellon> pabellones = (Set<Pabellon>) pabellonDAO.findPabellonesByDireccionLocalidad("PabellonLocalidad");

		Integer expectedResult = 2;

		assertEquals(expectedResult, pabellones.size());
	}

	@Test
	@DisplayName("Pabellon - findPabellonesByNombre")
	void findPabellonesByNombre() {
		Set<Pabellon> pabellones = (Set<Pabellon>) pabellonDAO.findPabellonesByNombre("pabellon_1");

		Integer expectedResult = 2;

		assertEquals(expectedResult, pabellones.size());
	}

}
