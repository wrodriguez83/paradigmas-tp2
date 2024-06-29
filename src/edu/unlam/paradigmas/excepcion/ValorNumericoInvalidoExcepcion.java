package edu.unlam.paradigmas.excepcion;

public class ValorNumericoInvalidoExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValorNumericoInvalidoExcepcion(String valor) {
		super(String.format("'%s' no es un valor numérico válido.", valor));
	}
}
