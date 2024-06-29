package edu.unlam.paradigmas.excepcion;

public class CriptomonedaExistenteExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CriptomonedaExistenteExcepcion(String simbolo) {
		super(String.format("La criptomoneda '%s' ya existe.", simbolo));
	}
}
