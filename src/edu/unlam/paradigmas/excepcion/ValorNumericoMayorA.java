package edu.unlam.paradigmas.excepcion;

import java.text.DecimalFormat;

public class ValorNumericoMayorA extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValorNumericoMayorA(Integer valor, Integer valorMaximo) {
		super(String.format("El valor '%s' no debe ser mayor a '%s.'", new DecimalFormat("0.####").format(valor),
				new DecimalFormat("0.####").format(valorMaximo)));
	}

	public ValorNumericoMayorA(Double valor, Double valorMaximo) {
		super(String.format("El valor '%s' no debe ser mayor a '%s.'.", new DecimalFormat("0.####").format(valor),
				new DecimalFormat("0.####").format(valorMaximo)));
	}
}
