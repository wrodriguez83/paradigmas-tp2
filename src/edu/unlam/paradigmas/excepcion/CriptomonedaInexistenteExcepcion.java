package edu.unlam.paradigmas.excepcion;

public class CriptomonedaInexistenteExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CriptomonedaInexistenteExcepcion() {
		super("La criptomoneda no existe.");
	}
}
