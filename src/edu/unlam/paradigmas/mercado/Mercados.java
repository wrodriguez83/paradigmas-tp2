package edu.unlam.paradigmas.mercado;

import java.util.ArrayList;
import java.util.List;

import edu.unlam.paradigmas.core.CSV;
import edu.unlam.paradigmas.core.Consola;

public class Mercados {

	private static final String ARCHIVO_CSV = "mercados.csv";
	private static List<Mercado> _lista;

	private static List<Mercado> datos() {
		if (Mercados._lista == null) {
			Mercados._lista = new ArrayList<>();
		}

		return Mercados._lista;
	}

	private static void cargar(String simbolo, Double capacidad, Double volumen, Double variacion) {
		Criptomoneda cripto = Criptomonedas.buscarPorSimbolo(simbolo);
		Mercados.cargar(cripto, capacidad, volumen, variacion);
	}

	private static void cargar(Criptomoneda cripto, Double capacidad, Double volumen, Double variacion) {
		Mercado entidad = Mercado.crear(cripto, capacidad, volumen, variacion);
		Mercados.datos().add(entidad);
	}

	public static void crear(Criptomoneda cripto, Double capacidad, Double volumen, Double variacion) {
		Mercados.cargar(cripto, capacidad, volumen, variacion);
		Mercados.guardarCSV();
	}

	public static Mercado buscarPorSimbolo(String simbolo) {
		return Mercados.datos().stream().filter(item -> item.criptomoneda.simbolo.equals(simbolo)).findFirst()
				.orElse(null);
	}

	public static Mercado buscarPorCriptomoneda(Criptomoneda cripto) {
		return Mercados.datos().stream().filter(item -> item.criptomoneda.equals(cripto)).findFirst().orElse(null);
	}

	public static void guardarCSV() {
		List<String> data = Mercados.datos().stream().map(item -> item.toCSVString()).toList();
		CSV.guardar(Mercados.ARCHIVO_CSV, data);
	}

	public static void cargarCSV() {
		Mercados.datos().clear();
		CSV.cargar(Mercados.ARCHIVO_CSV).forEach(item -> Mercados.cargar(item[0], Double.valueOf(item[1]),
				Double.valueOf(item[2]), Double.valueOf(item[3])));
	}

	public static void consultar() {
		Mercados.datos().forEach(item -> Consola.logLn(item.consultar()));
	}

	public static void consultar(Criptomoneda cripto) {
		Mercado mercado = Mercados.buscarPorCriptomoneda(cripto);
		Consola.logLn(mercado.consultar());
	}

//	public static Mercado crearPorConsola() {
//		Consola.titulo("Crear criptomoneda");
//
//		String simbolo = new EntradaSimboloCriptomoneda().solicitar("Ingrese el simbolo:");
//		String nombre = new EntradaString().solicitar("Ingrese el nombre:");
//		Double precio = new EntradaDoublePositivoCero().solicitar("Ingrese el precio:");
//
//		Mercado cripto = Mercados.crear(simbolo, nombre, precio);
//		Mercados.guardarCSV();
//
//		Consola.info("Criptomoneda creada correctamente!");
//		return cripto;
//	}
//
//	public static Mercado modificarSimboloPorConsola() {
//		Mercado cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:");
//		String nuevoSimbolo = new EntradaSimboloCriptomoneda().solicitar("Ingrese el nuevo simbolo:");
//
//		cripto.simbolo = nuevoSimbolo;
//		Mercados.guardarCSV();
//
//		Consola.info("Símbolo modificado correctamente!");
//		return cripto;
//	}
//
//	public static Mercado modificarNombrePorConsola() {
//		Mercado cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:");
//		String nuevoNombre = new EntradaString().solicitar("Ingrese el nuevo nombre:");
//
//		cripto.nombre = nuevoNombre;
//		Mercados.guardarCSV();
//
//		Consola.info("Nombre modificado correctamente!");
//		return cripto;
//	}
//
//	public static Mercado modificarPrecioPorConsola() {
//		Mercado cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:");
//		Double nuevoPrecio = new EntradaDoublePositivoCero().solicitar("Ingrese el nuevo precio:");
//
//		cripto.precio = nuevoPrecio;
//		Mercados.guardarCSV();
//
//		Consola.info("Precio modificado correctamente!");
//		return cripto;
//	}
//
	public static void eliminar(Criptomoneda cripto) {
		Mercado mercado = Mercados.buscarPorCriptomoneda(cripto);
		Mercados.datos().remove(mercado);
		Mercados.guardarCSV();
	}
//
//	public static void consultarCriptomoneda() {
//		Mercado cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:");
//		Consola.advertir(String.format("Nombre: %s\tSímbolo: %s\tPrecio en dólares: %s", cripto.nombre, cripto.simbolo,
//				new DecimalFormat("0.####").format(cripto.precio)));
//	}

	public static void comprar(Criptomoneda cripto, Double cantidad) {
		Mercado mercado = Mercados.buscarPorCriptomoneda(cripto);
		mercado.actualizar(mercado.capacidad - cantidad, mercado.volumen * 1.05, mercado.variacion * 1.05);

		Mercados.guardarCSV();
	}

	public static void vender(Criptomoneda cripto, Double cantidad) {
		Mercado mercado = Mercados.buscarPorCriptomoneda(cripto);
		mercado.actualizar(mercado.capacidad + cantidad, mercado.volumen * 0.93, mercado.variacion * 0.93);

		Mercados.guardarCSV();
	}
}
