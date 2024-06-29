package edu.unlam.paradigmas.excepciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.UsuarioExistenteExcepcion;

class UsuarioExistenteExcepcionTests {

	@Test
	void DebeMostrarElMensajeCorrectamente() {
		UsuarioExistenteExcepcion ex = assertThrows(UsuarioExistenteExcepcion.class, () -> {
			throw new UsuarioExistenteExcepcion("test");
		});

		assertEquals("El usuario 'test' ya existe.", ex.getMessage());
	}
}
