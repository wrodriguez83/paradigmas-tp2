package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;

class ValidarBooleanTests {
	ValidarBoolean validador = new ValidarBoolean();

	@Test
	void DebeAceptarElvalorIngresado() {
		Boolean dato = validador.validar("s");
		assertTrue(dato);

		dato = validador.validar("n");
		assertFalse(dato);

		dato = validador.validar("si");
		assertTrue(dato);

		dato = validador.validar("no");
		assertFalse(dato);

		dato = validador.validar("Si");
		assertTrue(dato);

		dato = validador.validar("nO");
		assertFalse(dato);
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		ValorInvalidoExcepcion ex = assertThrows(ValorInvalidoExcepcion.class, () -> {
			validador.validar("test com");
		});

		assertEquals("'test com' no es un valor válido.", ex.getMessage());
	}
}
