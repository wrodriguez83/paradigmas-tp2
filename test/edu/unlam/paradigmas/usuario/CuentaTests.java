package edu.unlam.paradigmas.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

class CuentaTests {
	@Test
	void DebeTirarExcepcionSiLosArgumentosSinInvalidos() {
		EntidadInvalidaExcepcion ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Cuenta(null, 0, 0);
		});

		assertEquals("'Cuenta' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());

		ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Cuenta("   ", 0, 0);
		});

		assertEquals("'Cuenta' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());

		ex = assertThrows(EntidadInvalidaExcepcion.class, () -> {
			new Cuenta("test", 0, 0);
		});

		assertEquals("'Cuenta' no se puede instanciar porque contiene argumentos inválidos.", ex.getMessage());
	}

	@Test
	void DebeCrearCorrectamente() throws EntidadInvalidaExcepcion {
		Cuenta cuenta = new Cuenta("test", 1234, 43.21);

		assertEquals("test", cuenta.banco);
		assertEquals(1234, cuenta.numero);
		assertEquals(43.21, cuenta.saldo);
	}

	@Test
	void DebeSerIgual() throws EntidadInvalidaExcepcion {
		Cuenta cuenta = new Cuenta("test", 1234, 43.21);
		Cuenta cuenta2 = new Cuenta("test", 1234, 43.21);

		assertTrue(cuenta.hashCode() == cuenta2.hashCode());
		assertTrue(cuenta.equals(cuenta2));
		assertTrue(cuenta.equals(cuenta));
	}

	@Test
	void NoDebeSerIgual() throws EntidadInvalidaExcepcion {
		Cuenta[] cuentas = new Cuenta[] { new Cuenta("test", 1234, 43.21), new Cuenta("test", 1234, 43.20),
				new Cuenta("test", 5678, 43.21), new Cuenta("test", 5678, 43.20), new Cuenta("test2", 1234, 43.21),
				new Cuenta("test2", 1234, 43.20), new Cuenta("test2", 5678, 43.21), new Cuenta("test2", 5678, 43.20) };

		for (int i = 1; i < cuentas.length; i++) {
			assertFalse(cuentas[0].hashCode() == cuentas[i].hashCode());
			assertFalse(cuentas[0].equals(cuentas[i]));
		}

		assertFalse(cuentas[0].equals(null));
		assertFalse(cuentas[0].equals(new Object()));

	}
}
