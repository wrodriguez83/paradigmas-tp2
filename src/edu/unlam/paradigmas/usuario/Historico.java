package edu.unlam.paradigmas.usuario;

import java.text.DecimalFormat;
import java.util.Objects;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Exportable;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;
import edu.unlam.paradigmas.moneda.Criptomoneda;

public class Historico implements Exportable {
	protected Criptomoneda criptomoneda;
	protected Double cantidad;

	protected Historico(Criptomoneda criptomoneda, Double cantidad) throws EntidadInvalidaExcepcion {
		if (Helper.esNuloOVacio(criptomoneda)) {
			throw new EntidadInvalidaExcepcion("Hisotrico");
		}

		this.criptomoneda = criptomoneda;
		this.cantidad = cantidad;
	}

	public Double getCantidad() {
		return cantidad;
	}

	@Override
	public String toCSVString() {
		return String.format("%s,%s", criptomoneda.getSimbolo(), cantidad);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, criptomoneda);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		return Objects.equals(cantidad, other.cantidad) && Objects.equals(criptomoneda, other.criptomoneda);
	}

	protected static Historico crear(Criptomoneda cripto, Double cantidad) {
		try {
			Historico entidad = new Historico(cripto, cantidad);

			return entidad;
		} catch (EntidadInvalidaExcepcion e) {
			Consola.error(e.getMessage());
		}

		return null;
	}

	protected String consultar() {
		return String.format("Criptomoneda: %-10s Cantidad: %-10s", criptomoneda.getSimbolo(),
				new DecimalFormat("0.####").format(cantidad));
	}

	public String getInformacionConCapacidad() {
		return String.format("Símbolo: %-10s Nombre: %-15s Precio en dólares: %-10s Capacidad: %-10s",
				criptomoneda.getSimbolo(), criptomoneda.getNombre(),
				new DecimalFormat("0.####").format(criptomoneda.getPrecio()),
				new DecimalFormat("0.####").format(cantidad));
	}
}
