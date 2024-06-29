package edu.unlam.paradigmas.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

class TraderTests {
	@Test
	void DebeTirarExcepcionSiLosArgumentosSinInvalidos() {
		EntidadInvalidaExcepcion ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Trader("test", null);
		});

		assertEquals("'Usuario' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());
	}

	@Test
	void DebeCrearCorrectamente() throws EntidadInvalidaExcepcion {
		Cuenta cuenta = new Cuenta("banco", 12344, 43.3);
		Trader usuario = new Trader("test", cuenta);

		assertEquals("test", usuario.nombre);
		assertEquals(cuenta, usuario.cuenta);

		Trader usuario2 = new Trader("test", "banco", 12344, 43.3);

		assertEquals("test", usuario2.nombre);
		assertTrue(cuenta.equals(usuario2.cuenta));
	}

	@Test
	void DebeSerIgual() throws EntidadInvalidaExcepcion {
		Cuenta cuenta = new Cuenta("banco", 12344, 43.3);
		Cuenta cuenta2 = new Cuenta("banco", 12344, 43.3);
		Trader usuario = new Trader("test", cuenta);
		Trader usuario2 = new Trader("test", cuenta2);

		assertTrue(usuario.hashCode() == usuario2.hashCode());
		assertTrue(usuario.equals(usuario2));
		assertTrue(usuario.equals(usuario));
	}

	@Test
	void NoDebeSerIgual() throws EntidadInvalidaExcepcion {
		Cuenta cuenta = new Cuenta("banco", 12344, 43.3);
		Cuenta cuenta2 = new Cuenta("banco2", 44321, 33.3);
		Trader usuario = new Trader("test", cuenta);
		Trader usuario2 = new Trader("test2", cuenta);
		Trader usuario3 = new Trader("test", cuenta2);
		Trader usuario4 = new Trader("test2", cuenta2);

		assertFalse(usuario.hashCode() == usuario2.hashCode());
		assertFalse(usuario.equals(null));
		assertFalse(usuario.equals(new Object()));
		assertFalse(usuario.equals(usuario2));
		assertFalse(usuario.equals(usuario3));
		assertFalse(usuario.equals(usuario4));
	}
}
