package edu.unlam.paradigmas.excepcion;

public class OpcionMenuInvalidoExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OpcionMenuInvalidoExcepcion(Integer valor) {
		super(String.format("'%s' no es una opción válida!", valor));
	}
}
