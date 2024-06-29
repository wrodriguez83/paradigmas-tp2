package edu.unlam.paradigmas.excepcion;

public class UsuarioExistenteExcepcion extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioExistenteExcepcion(String nombre) {
		super(String.format("El usuario '%s' ya existe.", nombre));
	}
}
