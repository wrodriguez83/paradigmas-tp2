package edu.unlam.paradigmas.excepcion;

public class ValorNumericoPositivoCeroInvalidoExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValorNumericoPositivoCeroInvalidoExcepcion() {
		super("Debe ser mayor o igual a cero.");
	}
}
