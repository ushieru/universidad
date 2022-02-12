package com.ibm.academia.restapi.universidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UniversidadApplicationTests {
	Calculadora calculadora = new Calculadora();

	@Test
	@DisplayName("calculadora")
	void sumarValores() {
		Integer valorA = 1;
		Integer valorB = 2;

		var result = calculadora.sumar(valorA, valorB);

		Integer resultExpected = 3;

		assertEquals(resultExpected, result);
	}

	class Calculadora {
		Integer sumar(Integer a, Integer b) {
			return a + b;
		}
	}
}
