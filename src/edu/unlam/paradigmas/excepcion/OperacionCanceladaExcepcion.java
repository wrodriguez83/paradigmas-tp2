package edu.unlam.paradigmas.excepcion;

public class OperacionCanceladaExcepcion extends Exception {
	private static final long serialVersionUID = 1L;

	public OperacionCanceladaExcepcion() {
		super("Operación cancelada");
	}
}
