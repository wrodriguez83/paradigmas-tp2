package edu.unlam.paradigmas.usuario;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;
import edu.unlam.paradigmas.mercado.Historico;

public class Trader extends Usuario {
	protected List<Historico> historico = new ArrayList<>();
	protected Cuenta cuenta;

	protected Trader(String nombre, Cuenta cuenta, List<Historico> historico) throws EntidadInvalidaExcepcion {
		super(nombre);

		if (Helper.esNulo(cuenta) || Helper.esNulo(historico)) {
			throw new EntidadInvalidaExcepcion("Usuario");
		}
		this.cuenta = cuenta;
		this.historico = historico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), cuenta, historico.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		boolean valido = super.equals(obj);

		if (valido) {
			Trader other = (Trader) obj;
			return Objects.equals(cuenta, other.cuenta) && Objects.equals(historico, other.historico);
		}

		return valido;
	}

	@Override
	public String toCSVString() {
		return String.format("trader,%s,%s,%s,%s", this.nombre, this.cuenta.banco,
				new DecimalFormat("0.####").format(this.cuenta.numero),
				new DecimalFormat("0.####").format(this.cuenta.saldo));
	}

	@Override
	public boolean esAdministrador() {
		return false;
	}

	@Override
	public boolean esTrader() {
		return true;
	}

	public Double getDiferenciaSaldo(Double monto) {
		return monto - cuenta.saldo;
	}

	public boolean puedeComprar(Double monto) {
		return monto <= cuenta.saldo;
	}

	protected static Trader crear(String nombre, Cuenta cuenta) {
		try {
			Trader entidad = new Trader(nombre, cuenta, new ArrayList<>());

			return entidad;
		} catch (EntidadInvalidaExcepcion e) {
			Consola.error(e.getMessage());
		}

		return null;
	}

	protected void agregarHistorico(Historico historico) {
		this.historico.add(historico);
	}

	@Override
	public void mostrarBienvenida() {
		Consola.titulo(String.format("Bienvenido %s! (Trader)", nombre));
	}
}
