package edu.unlam.paradigmas.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorNumericoPositivoInvalidoExcepcion;

class ValorNumericoPositivoInvalidoExcepcionTests {

	@Test
	void DebeMostrarElMensajeCorrectamente() {
		ValorNumericoPositivoInvalidoExcepcion ex = assertThrows(ValorNumericoPositivoInvalidoExcepcion.class, () -> {
			throw new ValorNumericoPositivoInvalidoExcepcion("test");
		});

		assertEquals("'test' debe ser mayor a cero.", ex.getMessage());
	}
}
