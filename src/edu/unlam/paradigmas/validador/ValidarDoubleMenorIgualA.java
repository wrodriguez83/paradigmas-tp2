package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.excepcion.ValorNumericoMayorA;

public class ValidarDoubleMenorIgualA extends Validador {
	private Double valor;

	public ValidarDoubleMenorIgualA(Double valor) {
		this.valor = valor;
	}

	@Override
	public boolean validar(Object valor) {
		boolean valido = (Double) valor <= this.valor;
		if (!valido) {
			throw new ValorNumericoMayorA((Double) valor, this.valor);
		}

		return valido;
	}
}
