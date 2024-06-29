package edu.unlam.paradigmas.usuario;

import java.util.ArrayList;
import java.util.List;

import edu.unlam.paradigmas.core.CSV;
import edu.unlam.paradigmas.core.Consola;

public class Usuarios {

	private static final String ARCHIVO_CSV = "usuarios.csv";
	private static List<Usuario> _lista;

	private static List<Usuario> datos() {
		if (Usuarios._lista == null) {
			Usuarios._lista = new ArrayList<>();
		}

		return Usuarios._lista;
	}

	public static void clear() {
		Usuarios.datos().clear();
	}

	public static Usuario cargar(String nombre) {
		Administrador usuario = Administrador.crear(nombre);
		Usuarios.datos().add(usuario);
		return usuario;
	}

	public static Usuario cargar(String nombre, String banco, Integer nroCuenta, Double saldo) {
		Cuenta cuenta = Cuenta.crear(banco, nroCuenta, saldo);
		Trader usuario = Trader.crear(nombre, cuenta);
		Usuarios.datos().add(usuario);
		return usuario;
	}

	public static Usuario crear(String nombre) {
		Usuario usuario = Usuarios.cargar(nombre);
		Usuarios.guardarCSV();

		return usuario;
	}

	public static Usuario crear(String nombre, String banco, Integer nroCuenta, Double saldo) {
		Usuario usuario = Usuarios.cargar(nombre, banco, nroCuenta, saldo);
		Usuarios.guardarCSV();
		return usuario;
	}

	public static Usuario buscarPorNombre(String nombre) {
		return Usuarios.datos().stream().filter(item -> item.nombre.equals(nombre)).findFirst().orElse(null);
	}

	public static void guardarCSV() {
		List<String> data = Usuarios.datos().stream().map(item -> item.toCSVString()).toList();
		CSV.guardar(Usuarios.ARCHIVO_CSV, data);
	}

	public static void cargarCSV() {
		CSV.cargar(Usuarios.ARCHIVO_CSV).forEach(item -> {
			if (item[0].equals("administrador")) {
				Usuarios.cargar(item[1]);
			} else {
				Usuarios.cargar(item[1], item[2], Integer.valueOf(item[3]), Double.valueOf(item[4]));
			}
		});
	}

	public static void sacarSaldo(Trader trader, Double monto) {
		trader.cuenta.actualizar(trader.cuenta.banco, trader.cuenta.numero, trader.cuenta.saldo - monto);
		Usuarios.guardarCSV();
	}

	public static void agregarSaldo(Trader trader, Double monto) {
		trader.cuenta.actualizar(trader.cuenta.banco, trader.cuenta.numero, trader.cuenta.saldo + monto);
		Usuarios.guardarCSV();
	}

	public static void adios() {
		Consola.logLn("Hasta luego!");
	}
}
