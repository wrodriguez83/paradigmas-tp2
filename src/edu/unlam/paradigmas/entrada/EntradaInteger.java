package edu.unlam.paradigmas.entrada;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;

public class EntradaInteger extends Entrada<Integer> {
	@Override
	protected Integer convertir(String valor) {
		Integer valorValido;
		try {
			valorValido = Integer.valueOf(valor);
		} catch (NumberFormatException e) {
			throw new ValorInvalidoExcepcion(valor);
		}

		return valorValido;
	}
}
