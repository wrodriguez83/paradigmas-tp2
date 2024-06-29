package edu.unlam.paradigmas.excepciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorNumericoInvalidoExcepcion;

class ValorNumericoInvalidoExcepcionTests {

	@Test
	void DebeMostrarElMensajeCorrectamente() {
		ValorNumericoInvalidoExcepcion ex = assertThrows(ValorNumericoInvalidoExcepcion.class, () -> {
			throw new ValorNumericoInvalidoExcepcion("test");
		});

		assertEquals("'test' no es un valor numérico válido.", ex.getMessage());
	}
}
