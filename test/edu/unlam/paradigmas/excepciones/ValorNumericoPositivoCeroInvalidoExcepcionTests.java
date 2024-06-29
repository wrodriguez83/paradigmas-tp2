package edu.unlam.paradigmas.excepciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorNumericoPositivoCeroInvalidoExcepcion;

class ValorNumericoPositivoCeroInvalidoExcepcionTests {

	@Test
	void DebeMostrarElMensajeCorrectamente() {
		ValorNumericoPositivoCeroInvalidoExcepcion ex = assertThrows(ValorNumericoPositivoCeroInvalidoExcepcion.class,
				() -> {
					throw new ValorNumericoPositivoCeroInvalidoExcepcion("test");
				});

		assertEquals("'test' debe ser mayor o igual a cero.", ex.getMessage());
	}
}
