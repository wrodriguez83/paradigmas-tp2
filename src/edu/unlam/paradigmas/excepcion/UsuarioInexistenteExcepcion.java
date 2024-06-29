package edu.unlam.paradigmas.excepcion;

public class UsuarioInexistenteExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioInexistenteExcepcion() {
		super("El usuario no existe.");
	}
}
