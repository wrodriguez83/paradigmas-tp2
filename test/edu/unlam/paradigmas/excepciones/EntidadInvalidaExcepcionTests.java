package edu.unlam.paradigmas.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

class EntidadInvalidaExcepcionTests {

	@Test
	void DebeMostrarElMensajeCorrectamente() {
		EntidadInvalidaExcepcion ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			throw new EntidadInvalidaExcepcion("test");
		});

		assertEquals("'test' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());
	}

}
