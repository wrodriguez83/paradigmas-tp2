package edu.unlam.paradigmas.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

class AdministradorTests {
	@Test
	void DebeTirarExcepcionSiLosArgumentosSinInvalidos() {
		EntidadInvalidaExcepcion ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Administrador(null);
		});

		assertEquals("'Usuario' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());

		ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Administrador("   ");
		});

		assertEquals("'Usuario' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());
	}

	@Test
	void DebeCrearCorrectamente() throws EntidadInvalidaExcepcion {
		Administrador usuario = new Administrador("test");

		assertEquals("test", usuario.nombre);
	}
}
