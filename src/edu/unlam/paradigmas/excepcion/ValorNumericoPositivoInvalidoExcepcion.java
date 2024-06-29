package edu.unlam.paradigmas.excepcion;

public class ValorNumericoPositivoInvalidoExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValorNumericoPositivoInvalidoExcepcion() {
		super("Debe ser mayor a cero.");
	}
}
