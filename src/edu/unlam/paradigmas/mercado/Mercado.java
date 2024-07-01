package edu.unlam.paradigmas.mercado;

import java.text.DecimalFormat;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Exportable;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

public class Mercado implements Exportable {

	protected Criptomoneda criptomoneda;
	protected Double capacidad;
	protected Double volumen;
	protected Double variacion;

	protected Mercado(Criptomoneda criptomoneda, Double capacidad, Double volumen, Double variacion)
			throws EntidadInvalidaExcepcion {
		if (Helper.esNulo(criptomoneda)) {
			throw new EntidadInvalidaExcepcion("Mercado");
		}

		this.criptomoneda = criptomoneda;
		actualizar(capacidad, volumen, variacion);
	}

	public Double getCapacidad() {
		return capacidad;
	}

	@Override
	public String toCSVString() {
		return String.format("%s,%s,%s,%s", criptomoneda.simbolo, new DecimalFormat("0.####").format(capacidad),
				new DecimalFormat("0.####").format(volumen), new DecimalFormat("0.####").format(variacion));
	}

	protected static Mercado crear(Criptomoneda criptomoneda, Double capacidad, Double volumen, Double variacion) {
		try {
			Mercado entidad = new Mercado(criptomoneda, capacidad, volumen, variacion);

			return entidad;
		} catch (EntidadInvalidaExcepcion e) {
			Consola.error(e.getMessage());
		}

		return null;
	}

	protected String consultar() {
		return String.format("Criptomoneda: %-10s Capacidad: %-10s Volumen 24hs: %-10s Variación 7 dias: %-10s",
				criptomoneda.simbolo, new DecimalFormat("0.####").format(capacidad),
				new DecimalFormat("0.####%").format(volumen), new DecimalFormat("0.####%").format(variacion));
	}

	public final void actualizar(Double capacidad, Double volumen, Double variacion) {
		this.capacidad = capacidad;
		this.volumen = volumen;
		this.variacion = variacion;
	}
}
