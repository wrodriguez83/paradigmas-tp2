package edu.unlam.paradigmas.entrada;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;

public class EntradaDouble extends Entrada<Double> {
	@Override
	protected Double convertir(String valor) {
		Double valorValido;
		try {
			valorValido = Double.valueOf(valor);
		} catch (NumberFormatException e) {
			throw new ValorInvalidoExcepcion(valor);
		}

		return valorValido;
	}
}
