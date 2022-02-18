package com.ibm.academia.restapi.universidad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AulaTest {

	@Autowired
	AulaDAO aulaDAO;

	@BeforeAll
	public void init() {
		Direccion direccion = new Direccion("calle", "numero", "codigoPostal", "departamento", "piso", "localidad");
		Pabellon pabellon_0 = new Pabellon(null, 10.0, "nombre", direccion, "root");
		Pabellon pabellon_1 = new Pabellon(null, 10.0, "nombre", direccion, "root");
		Aula aula_0 = new Aula(null, 1, "10x10", 5, TipoPizarron.PIZARRON_BLANCO, "root");
		aula_0.setPabellon(pabellon_0);
		Aula aula_1 = new Aula(null, 2, "10x10", 5, TipoPizarron.PIZARRON_BLANCO, "root");
		aula_1.setPabellon(pabellon_1);
		Aula aula_2 = new Aula(null, 3, "10x10", 5, TipoPizarron.PIZARRON_TIZA, "root");

		aulaDAO.guardar(aula_0);
		aulaDAO.guardar(aula_1);
		aulaDAO.guardar(aula_2);

		assertNotNull(aula_0.getId());
		assertNotNull(aula_1.getId());
		assertNotNull(aula_2.getId());
	}

	@Test
	@DisplayName("Alumno - findAulasByTipoPizarron")
	void alumnoFindAulasByTipoPizarron() {
		Set<Aula> aulas = (Set<Aula>) aulaDAO.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);

		Integer expectedResult = 2;

		assertEquals(expectedResult, aulas.size());
	}

	@Test
	@DisplayName("Alumno - findAulasByPabellonNombre")
	void alumnoFindAulasByPabellonNombre() {
		Set<Aula> aulas = (Set<Aula>) aulaDAO.findAulasByPabellonNombre("nombre");

		Integer expectedResult = 2;

		assertEquals(expectedResult, aulas.size());
	}

	@Test
	@DisplayName("Alumno - findAulasByNumAula")
	void alumnoFindAulasByNumAula() {
		Set<Aula> aulas = (Set<Aula>) aulaDAO.findAulasByNumAula(3);

		Integer expectedResult = 1;

		assertEquals(expectedResult, aulas.size());
	}

}
