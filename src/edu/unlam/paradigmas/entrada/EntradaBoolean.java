package edu.unlam.paradigmas.entrada;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;

public class EntradaBoolean extends Entrada<Boolean> {
	@Override
	protected Boolean convertir(String valor) {
		Boolean valorValido;
		switch (valor.toLowerCase()) {
		case "s", "si" -> valorValido = true;
		case "n", "no" -> valorValido = false;
		default -> throw new ValorInvalidoExcepcion(valor);
		}

		return valorValido;
	}
}
