package edu.unlam.paradigmas.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

class UsuarioTests {
	@Test
	void DebeTirarExcepcionSiLosArgumentosSinInvalidos() {
		EntidadInvalidaExcepcion ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Usuario(null);
		});

		assertEquals("'Usuario' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());

		ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Usuario("   ");
		});

		assertEquals("'Usuario' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());
	}

	@Test
	void DebeCrearCorrectamente() throws EntidadInvalidaExcepcion {
		Usuario usuario = new Usuario("test");

		assertEquals("test", usuario.nombre);
	}

	@Test
	void DebeSerIgual() throws EntidadInvalidaExcepcion {
		Usuario usuario = new Usuario("test");
		Usuario usuario2 = new Usuario("test");

		assertTrue(usuario.hashCode() == usuario2.hashCode());
		assertTrue(usuario.equals(usuario2));
		assertTrue(usuario.equals(usuario));
	}
	
	@Test
	void NoDebeSerIgual() throws EntidadInvalidaExcepcion {
		Usuario usuario = new Usuario("test");
		Usuario usuario2 = new Usuario("test2");

		assertFalse(usuario.hashCode() == usuario2.hashCode());
		assertFalse(usuario.equals(null));
		assertFalse(usuario.equals(new Object()));
		assertFalse(usuario.equals(usuario2));
	}
}
