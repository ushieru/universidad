package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestPabellon implements CommandLineRunner {

	@Autowired
	private PabellonDAO pabellonDao;

	@Override
	public void run(String... args) throws Exception {
		var pabellonesByLocalidad = pabellonDao.findPabellonesByDireccionLocalidad("localidad");
		var pabellonesByName = pabellonDao.findPabellonesByNombre("nombre");

		System.out.println("--- findPabellonesByLocalidad ---");
		pabellonesByLocalidad.forEach(System.out::println);

		System.out.println("--- findPabellonesByNombre ---");
		pabellonesByName.forEach(System.out::println);

	}
}
