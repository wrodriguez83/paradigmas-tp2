package edu.unlam.paradigmas.usuario;

import java.util.Objects;

import edu.unlam.paradigmas.core.Exportable;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

public abstract class Usuario implements Exportable {
	protected String nombre;

	protected Usuario(String nombre) throws EntidadInvalidaExcepcion {
		if (Helper.esNuloOVacio(nombre)) {
			throw new EntidadInvalidaExcepcion("Usuario");
		}

		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public abstract boolean esAdministrador();

	public abstract boolean esTrader();

	public abstract void mostrarBienvenida();
}
