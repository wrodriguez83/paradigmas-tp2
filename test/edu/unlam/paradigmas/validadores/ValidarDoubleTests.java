package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorNumericoInvalidoExcepcion;

class ValidarDoubleTests {
	ValidarDouble validador = new ValidarDouble();

	@Test
	void DebeAceptarElvalorIngresado() {
		Double dato = validador.validar("123.2");
		assertEquals(123.2,dato);

		dato = validador.validar("123");
		assertEquals(123,dato);
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		ValorNumericoInvalidoExcepcion ex = assertThrows(ValorNumericoInvalidoExcepcion.class, () -> {
			validador.validar("test com");
		});

		assertEquals("'test com' no es un valor numérico válido.", ex.getMessage());
	}
}
