package edu.unlam.paradigmas.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.test.ConsolaMock;

class ConsolaTests extends ConsolaMock {
	@Test
	void DebeMostrarElMensajeEnUnaLinea() {
		Consola.log("Mensaje prueba");
		Consola.log("Mensaje prueba2");

		assertEquals("Mensaje pruebaMensaje prueba2", getOutput());
	}

	@Test
	void DebeMostrarElMensajeEnDosLineas() {
		Consola.logLn("Mensaje prueba");
		Consola.logLn("Mensaje prueba2");

		assertEquals("Mensaje prueba\nMensaje prueba2", getOutput());
	}

	@Test
	void DebeMostrarElMensajeComoUnTitulo() {
		Consola.titulo("Mensaje prueba");

		assertEquals("Mensaje prueba\n--------------", getOutput());
	}

	@Test
	void DebeMostrarElMensajeComoUnError() {
		Consola.error("Mensaje prueba");

		assertEquals("!! ERROR: Mensaje prueba", getOutput());
	}

	@Test
	void DebeMostrarElMensajeComoUnaAdvertencia() {
		Consola.advertir("Mensaje prueba");

		assertEquals("Mensaje prueba", getOutput());
	}

	@Test
	void DebeMostrarTodoJunto() {
		Consola.log("Mensaje prueba");
		Consola.log("Mensaje prueba2");
		Consola.logLn("Mensaje prueba");
		Consola.logLn("Mensaje prueba2");
		Consola.titulo("Mensaje prueba");
		Consola.error("Mensaje prueba");
		Consola.advertir("Mensaje prueba");

		assertEquals("Mensaje pruebaMensaje prueba2Mensaje prueba\nMensaje prueba2\n\nMensaje prueba\n"
				+ "--------------\n\n!! ERROR: Mensaje prueba\n\n\nMensaje prueba", getOutput());
	}

	@Test
	void DebePedirDato() {
		Consola.scanner = new Scanner("test, test");
		String dato = Consola.obtener();

		assertEquals("test, test", dato);
		assertEquals("", getOutput());
	}

	@Test
	void DebePedirDatoConMensaje() {
		Consola.scanner = new Scanner("test, test");
		String dato = Consola.obtener("MensajeTest:");

		assertEquals("test, test", dato);
		assertEquals("MensajeTest:", getOutput());
	}
}
