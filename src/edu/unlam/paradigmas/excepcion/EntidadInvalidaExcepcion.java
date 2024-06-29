package edu.unlam.paradigmas.excepcion;

public class EntidadInvalidaExcepcion extends Exception {
	private static final long serialVersionUID = 1L;

	public EntidadInvalidaExcepcion(String nombre) {
		super(String.format("'%s' no se puede instanciar porque contiene argumentos inválidos.", nombre));
	}
}
