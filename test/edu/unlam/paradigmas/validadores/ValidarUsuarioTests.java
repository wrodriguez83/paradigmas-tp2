package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;
import edu.unlam.paradigmas.usuario.Administrador;
import edu.unlam.paradigmas.usuario.Usuario;
import edu.unlam.paradigmas.usuario.Usuarios;

class ValidarUsuarioTests {
	ValidarUsuario validador = new ValidarUsuario();

	@Test
	void DebeAceptarElvalorIngresado() {
		Usuarios.clear();
		Administrador usuario = (Administrador) Usuarios.crear("test");
		Administrador usuario2 = (Administrador) validador.validar("test");
		assertTrue(usuario.equals(usuario2));
		
		Usuarios.clear();
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		ValorInvalidoExcepcion ex = assertThrows(ValorInvalidoExcepcion.class, () -> {
			validador.validar("test, com");
		});

		assertEquals("',' no es un valor válido.", ex.getMessage());
	}
}
