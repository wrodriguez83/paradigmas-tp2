package edu.unlam.paradigmas.excepcion;

public class ValorInvalidoExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValorInvalidoExcepcion(String valor) {
		super(String.format("'%s' no es un valor válido.", valor));
	}
}
