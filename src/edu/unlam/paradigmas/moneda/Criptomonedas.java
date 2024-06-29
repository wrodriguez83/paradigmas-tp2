package edu.unlam.paradigmas.moneda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import edu.unlam.paradigmas.core.CSV;
import edu.unlam.paradigmas.core.Consola;

public class Criptomonedas {

	private static final String ARCHIVO_CSV = "criptomonedas.csv";
	private static List<Criptomoneda> _lista;

	private static List<Criptomoneda> datos() {
		if (Criptomonedas._lista == null) {
			Criptomonedas._lista = new ArrayList<>();
		}

		return Criptomonedas._lista;
	}

	public static Criptomoneda cargar(String simbolo, String nombre, Double precio) {
		Criptomoneda entidad = Criptomoneda.crear(simbolo, nombre, precio);
		Criptomonedas.datos().add(entidad);
		return entidad;
	}

	public static void crear(String simbolo, String nombre, Double precio) {
		Criptomoneda entidad = Criptomonedas.cargar(simbolo, nombre, precio);
		Mercados.crear(entidad, 500d, 1d, 1d);

		Criptomonedas.guardarCSV();
		Mercados.guardarCSV();
	}

	public static Criptomoneda buscarPorSimbolo(String simbolo) {
		return Criptomonedas.datos().stream().filter(item -> item.simbolo.toLowerCase().equals(simbolo.toLowerCase()))
				.findFirst().orElse(null);
	}

	public static void guardarCSV() {
		List<String> data = Criptomonedas.datos().stream().map(item -> item.toCSVString()).toList();
		CSV.guardar(Criptomonedas.ARCHIVO_CSV, data);
	}

	public static void cargarCSV() {
		Criptomonedas.datos().clear();
		CSV.cargar(Criptomonedas.ARCHIVO_CSV)
				.forEach(item -> Criptomonedas.cargar(item[0], item[1], Double.valueOf(item[2])));
	}

	public static void modificarSimbolo(Criptomoneda cripto, String nuevoSimbolo) {
		cripto.actualizar(nuevoSimbolo, cripto.nombre, cripto.precio);

		Criptomonedas.guardarCSV();
		Mercados.guardarCSV();
	}

	public static void modificarNombre(Criptomoneda cripto, String nuevoNombre) {
		cripto.actualizar(cripto.simbolo, nuevoNombre, cripto.precio);
		Criptomonedas.guardarCSV();
	}

	public static void modificarPrecio(Criptomoneda cripto, Double nuevoPrecio) {
		cripto.actualizar(cripto.simbolo, cripto.nombre, nuevoPrecio);
		Criptomonedas.guardarCSV();
	}

	public static void eliminar(Criptomoneda cripto) {
		Criptomonedas.datos().remove(cripto);
		Mercados.eliminar(cripto);

		Criptomonedas.guardarCSV();
	}

	public static void comprar(Criptomoneda cripto, Double cantidad) {
		cripto.comprar(cantidad);
		Criptomonedas.guardarCSV();

		Mercados.comprar(cripto, cantidad);
	}

	public static void recomendar() {
		List<Mercado> mercados = new ArrayList<>();
		for (Criptomoneda cripto : Criptomonedas.datos()) {
			Mercado mercado = Mercados.buscarPorCriptomoneda(cripto);
			if (mercados.isEmpty() || Objects.equals(mercados.get(0).criptomoneda.precio, cripto.precio)) {
				mercados.add(mercado);
			} else if (mercados.get(0).criptomoneda.precio < cripto.precio) {
				mercados.clear();
				mercados.add(mercado);

			}
		}

		List<Recomendacion> recomendaciones = Criptomonedas.datos().stream().map(item -> {
			List<Double> coeficientes = mercados.stream().map(mercado -> (mercado.capacidad / item.precio) * 100)
					.collect(Collectors.toList());

			Collections.sort(coeficientes);

			Recomendacion recomendacion = Recomendacion.crear(item, coeficientes.get(coeficientes.size() - 1));
			return recomendacion;
//			return 1;
//			return Recomendacion.crear(item, coeficientes.getFirst());
		}).toList();

		List<Recomendacion> losRecomendados = new ArrayList<>();
		for (Recomendacion recomendacion : recomendaciones) {
			if (losRecomendados.isEmpty()
					|| Objects.equals(losRecomendados.get(0).coeficiente, recomendacion.coeficiente)) {
				losRecomendados.add(recomendacion);
			} else if (losRecomendados.get(0).coeficiente < recomendacion.coeficiente) {
				losRecomendados.clear();
				losRecomendados.add(recomendacion);
			}
		}

		losRecomendados.forEach(item -> Consola.info(item.getInformacion()));
	}
}
