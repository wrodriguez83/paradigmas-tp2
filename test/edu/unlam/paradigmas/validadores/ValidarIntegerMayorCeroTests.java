package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorNumericoInvalidoExcepcion;
import edu.unlam.paradigmas.excepcion.ValorNumericoPositivoInvalidoExcepcion;
import edu.unlam.paradigmas.validador.ValidarIntegerPositivo;

class ValidarIntegerMayorCeroTests {
	ValidarIntegerPositivo validador = new ValidarIntegerPositivo();

	@Test
	void DebeAceptarElvalorIngresado() {
		Integer dato = validador.validar("123");
		assertEquals(123, dato);
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		ValorNumericoInvalidoExcepcion ex = assertThrows(ValorNumericoInvalidoExcepcion.class, () -> {
			validador.validar("test com");
		});

		assertEquals("'test com' no es un valor numérico válido.", ex.getMessage());

		ValorNumericoPositivoInvalidoExcepcion ex2 = assertThrows(ValorNumericoPositivoInvalidoExcepcion.class, () -> {
			validador.validar("0");
		});

		assertEquals("'0' debe ser mayor a cero.", ex2.getMessage());
	}
}
