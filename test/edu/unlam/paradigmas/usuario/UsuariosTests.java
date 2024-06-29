package edu.unlam.paradigmas.usuario;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.test.ConsolaMock;

class UsuariosTests extends ConsolaMock {
	@AfterEach
	@Override
	protected void AfterEach() {
		super.AfterEach();
		Usuarios.clear();
	}
	
	@Test
	void DebeTirarExcepcionSiLosArgumentosSinInvalidos() {
		Usuarios.crear(null);
		assertEquals("!! ERROR: 'Usuario' no se puede instanciar porque contiene argumentos inválidos.", getOutput());
		resetOutput();

		Usuarios.crear(null, "banco", 1, 0d);
		assertEquals("!! ERROR: 'Usuario' no se puede instanciar porque contiene argumentos inválidos.", getOutput());

		resetOutput();

		Usuarios.crear(null, null, 0, 0d);
		assertEquals("!! ERROR: 'Cuenta' no se puede instanciar porque contiene argumentos inválidos.", getOutput());
	}

	@Test
	void DebeCrearCorrectamenteAdministrador() {
		Usuario usuario = Usuarios.crear("test");

		assertEquals("test", usuario.nombre);
	}

	@Test
	void DebeCrearCorrectamenteTrader() {
		Trader usuario = (Trader) Usuarios.crear("test", "banco", 12344, 43.3);

		assertEquals("test", usuario.nombre);
		assertEquals("banco", usuario.cuenta.banco);
		assertEquals(12344, usuario.cuenta.numero);
		assertEquals(43.3, usuario.cuenta.saldo);
	}

	@Test
	void DebeBuscarCorrectamente() {
		Usuarios.crear("test", "banco", 12344, 43.3);
		Usuarios.crear("test2");

		Trader usuario = (Trader) Usuarios.buscarPorNombre("test");

		assertEquals("test", usuario.nombre);
		assertEquals("banco", usuario.cuenta.banco);
		assertEquals(12344, usuario.cuenta.numero);
		assertEquals(43.3, usuario.cuenta.saldo);
	}

	@Test
	void NoDebeBuscarCorrectamente() {
		Usuarios.crear("test2");

		Trader usuario = (Trader) Usuarios.buscarPorNombre("test");

		assertNull(usuario);
	}
}
