package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestProfesores implements CommandLineRunner {

	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO personaDao;

	@Override
	public void run(String... args) throws Exception {
		Iterable<Persona> profesores = ((ProfesorDAO) personaDao).findProfesoresByCarrera("Ingenieria en computacion");
		profesores.forEach(System.out::println);
	}
}
