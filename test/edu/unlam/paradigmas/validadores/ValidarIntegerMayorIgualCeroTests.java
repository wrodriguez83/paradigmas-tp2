package edu.unlam.paradigmas.validadores;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.excepcion.ValorNumericoInvalidoExcepcion;
import edu.unlam.paradigmas.excepcion.ValorNumericoPositivoCeroInvalidoExcepcion;
import edu.unlam.paradigmas.validador.ValidarIntegerPositivoCero;

class ValidarIntegerMayorIgualCeroTests {
	ValidarIntegerPositivoCero validador = new ValidarIntegerPositivoCero();

	@Test
	void DebeAceptarElvalorIngresado() {
		Integer dato = validador.validar("123");
		assertEquals(123, dato);

		dato = validador.validar("0");
		assertEquals(0, dato);
	}

	@Test
	void NoDebeAceptarElvalorIngresado() {
		ValorNumericoInvalidoExcepcion ex = assertThrows(ValorNumericoInvalidoExcepcion.class, () -> {
			validador.validar("test com");
		});

		assertEquals("'test com' no es un valor numérico válido.", ex.getMessage());

		ex = assertThrows(ValorNumericoInvalidoExcepcion.class, () -> {
			validador.validar("123.3");
		});

		assertEquals("'123.3' no es un valor numérico válido.", ex.getMessage());

		ValorNumericoPositivoCeroInvalidoExcepcion ex2 = assertThrows(ValorNumericoPositivoCeroInvalidoExcepcion.class,
				() -> {
					validador.validar("-123");
				});

		assertEquals("'-123' debe ser mayor o igual a cero.", ex2.getMessage());
	}
}
