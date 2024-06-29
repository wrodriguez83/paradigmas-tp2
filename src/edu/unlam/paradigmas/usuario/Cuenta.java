package edu.unlam.paradigmas.usuario;

import java.util.Objects;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

public class Cuenta {

	protected String banco;
	protected Integer numero;
	protected double saldo;

	protected Cuenta(String banco, Integer numero, double saldo) throws EntidadInvalidaExcepcion {
		if (Helper.esNuloOVacio(banco) || numero == 0) {
			throw new EntidadInvalidaExcepcion("Cuenta");
		}

		actualizar(banco, numero, saldo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(banco, numero, saldo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Cuenta other = (Cuenta) obj;
		return Objects.equals(banco, other.banco)
				&& Double.doubleToLongBits(numero) == Double.doubleToLongBits(other.numero)
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo);
	}

	protected static Cuenta crear(String banco, Integer numero, double saldo) {
		try {
			Cuenta entidad = new Cuenta(banco, numero, saldo);

			return entidad;
		} catch (EntidadInvalidaExcepcion e) {
			Consola.error(e.getMessage());
		}

		return null;
	}

	public final void actualizar(String banco, Integer numero, double saldo) {
		this.banco = banco;
		this.numero = numero;
		this.saldo = saldo;
	}
}
