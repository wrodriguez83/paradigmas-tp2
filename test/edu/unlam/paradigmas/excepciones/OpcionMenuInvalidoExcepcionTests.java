package edu.unlam.paradigmas.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.OpcionMenuInvalidoExcepcion;

class OpcionMenuInvalidoExcepcionTests {

	@Test
	void DebeMostrarElMensajeCorrectamente() {
		OpcionMenuInvalidoExcepcion ex = assertThrows(OpcionMenuInvalidoExcepcion.class, () -> {
			throw new OpcionMenuInvalidoExcepcion("test");
		});

		assertEquals("'test' no es una opción válida!", ex.getMessage());
	}

}
