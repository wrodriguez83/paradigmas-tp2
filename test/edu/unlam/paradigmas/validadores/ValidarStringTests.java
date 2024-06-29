package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;
import edu.unlam.paradigmas.validador.ValidarString;

class ValidarStringTests {
	ValidarString validador = new ValidarString();

	@Test
	void DebeAceptarElvalorIngresado() {
		String dato = validador.validar("test ok");
		assertEquals("test ok",dato);
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		ValorInvalidoExcepcion ex = assertThrows(ValorInvalidoExcepcion.class, () -> {
			validador.validar("test, com");
		});

		assertEquals("',' no es un valor válido.", ex.getMessage());
	}
}
