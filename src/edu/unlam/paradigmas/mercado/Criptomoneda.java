package edu.unlam.paradigmas.mercado;

import java.text.DecimalFormat;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Exportable;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

public class Criptomoneda implements Exportable {

	protected String simbolo;
	protected String nombre;
	protected Double precio;

	public Criptomoneda(String simbolo, String nombre, Double precio) throws EntidadInvalidaExcepcion {
		if (Helper.esNuloOVacio(simbolo) || Helper.esNuloOVacio(nombre)) {
			throw new EntidadInvalidaExcepcion("Criptomoneda");
		}

		actualizar(simbolo, nombre, precio);
	}

	@Override
	public String toCSVString() {
		return String.format("%s,%s,%s", simbolo, nombre, new DecimalFormat("0.####").format(precio));
	}

	public String getSimbolo() {
		return simbolo;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public final void actualizar(String simbolo, String nombre, Double precio) {
		this.simbolo = simbolo.toUpperCase();
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getInformacion() {
		return String.format("Símbolo: %-10s Nombre: %-15s Precio en dólares: %-10s", simbolo, nombre,
				new DecimalFormat("0.####").format(precio));
	}

	public String getInformacionConCapacidad() {
		Mercado mercado = Mercados.buscarPorCriptomoneda(this);
		return String.format("Símbolo: %-10s Nombre: %-15s Precio en dólares: %-10s Capacidad: %-10s", nombre, simbolo,
				new DecimalFormat("0.####").format(precio), new DecimalFormat("0.####").format(mercado.capacidad));
	}

	public void comprar(Double cantidad) {
		if (cantidad > 1000) {
			actualizar(simbolo, nombre, precio * 0.1);
		}
	}

	public Double calcularTotal(Double cantidad) {
		return cantidad * precio;
	}

	protected static Criptomoneda crear(String simbolo, String nombre, Double precio) {
		try {
			Criptomoneda entidad = new Criptomoneda(simbolo, nombre, precio);

			return entidad;
		} catch (EntidadInvalidaExcepcion e) {
			Consola.error(e.getMessage());
		}

		return null;
	}
}
