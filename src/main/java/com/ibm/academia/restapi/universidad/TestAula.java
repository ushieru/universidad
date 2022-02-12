package com.ibm.academia.restapi.universidad;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoPizarron;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestAula implements CommandLineRunner {

	@Autowired
	private AulaDAO aulaDao;

	@Override
	public void run(String... args) throws Exception {
		var aulasByNum = aulaDao.findAulasByNumAula(1);
		var aulasByPabellon = aulaDao.findAulasByPabellonNombre("nombre");
		var aulasByPizarron = aulaDao.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);

		System.out.println("--- findAulasByNumAula ---");
		aulasByNum.forEach(System.out::println);

		System.out.println("--- findAulasByPabellonNombre ---");
		aulasByPabellon.forEach(System.out::println);

		System.out.println("--- findAulasByTipoPizarron ---");
		aulasByPizarron.forEach(System.out::println);
	}
}
