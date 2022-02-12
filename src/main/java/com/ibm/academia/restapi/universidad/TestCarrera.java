package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestCarrera implements CommandLineRunner {

	@Autowired
	private CarreraDAO carreraDao;

	@Override
	public void run(String... args) throws Exception {
		var carreras = carreraDao.buscarCarrerasPorProfesorNombreYApellido("nombre", "apellido");
		carreras.forEach(System.out::println);
	}
}
