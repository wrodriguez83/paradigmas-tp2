package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.UsuarioExistenteExcepcion;
import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;
import edu.unlam.paradigmas.usuario.Usuarios;
import edu.unlam.paradigmas.validador.ValidarNombreUsuario;

class ValidarNombreUsuarioTests {
	ValidarNombreUsuario validador = new ValidarNombreUsuario();

	@AfterEach
	void AfterEach() {
		Usuarios.clear();
	}

	@Test
	void DebeAceptarElvalorIngresado() {
		String nombre = validador.validar("test");
		assertEquals("test", nombre);
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		Usuarios.crear("test");
		
		ValorInvalidoExcepcion ex = assertThrows(ValorInvalidoExcepcion.class, () -> {
			validador.validar("test, com");
		});

		assertEquals("',' no es un valor válido.", ex.getMessage());

		UsuarioExistenteExcepcion ex2 = assertThrows(UsuarioExistenteExcepcion.class, () -> {
			validador.validar("test");
		});

		assertEquals("El usuario 'test' ya existe.", ex2.getMessage());
	}
}
