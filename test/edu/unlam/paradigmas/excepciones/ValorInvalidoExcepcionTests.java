package edu.unlam.paradigmas.excepciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;

class ValorInvalidoExcepcionTests {

	@Test
	void DebeMostrarElMensajeCorrectamente() {
		ValorInvalidoExcepcion ex = assertThrows(ValorInvalidoExcepcion.class, () -> {
			throw new ValorInvalidoExcepcion("test");
		});

		assertEquals("'test' no es un valor válido.", ex.getMessage());
	}
}
