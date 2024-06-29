package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.OpcionMenuInvalidoExcepcion;
import edu.unlam.paradigmas.excepcion.ValorNumericoInvalidoExcepcion;
import edu.unlam.paradigmas.excepcion.ValorNumericoPositivoInvalidoExcepcion;
import edu.unlam.paradigmas.validador.ValidarMenu;

class ValidarMenuTests {
	ValidarMenu validador = new ValidarMenu(3);

	@Test
	void DebeAceptarElvalorIngresado() {
		Integer dato = validador.validar("1");
		assertEquals(1, dato);

		dato = validador.validar("2");
		assertEquals(2, dato);

		dato = validador.validar("3");
		assertEquals(3, dato);
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		ValorNumericoInvalidoExcepcion ex = assertThrows(ValorNumericoInvalidoExcepcion.class, () -> {
			validador.validar("test com");
		});

		assertEquals("'test com' no es un valor numérico válido.", ex.getMessage());

		ValorNumericoPositivoInvalidoExcepcion ex2 = assertThrows(ValorNumericoPositivoInvalidoExcepcion.class, () -> {
			validador.validar("-1");
		});

		assertEquals("'-1' debe ser mayor a cero.", ex2.getMessage());

		ex2 = assertThrows(ValorNumericoPositivoInvalidoExcepcion.class, () -> {
			validador.validar("0");
		});

		assertEquals("'0' debe ser mayor a cero.", ex2.getMessage());

		OpcionMenuInvalidoExcepcion ex3 = assertThrows(OpcionMenuInvalidoExcepcion.class, () -> {
			validador.validar("5");
		});

		assertEquals("'5' no es una opción válida!", ex3.getMessage());
	}
}
