package edu.unlam.paradigmas.mercado;

import java.util.Comparator;
import java.util.List;

import edu.unlam.paradigmas.core.CSV;
import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.usuario.Trader;

public class UsuariosHistorico {
//	private static final String ARCHIVO_CSV = "_historico.csv";
//
//	public static Usuario cargar(String nombre) {
//		Administrador usuario = Administrador.crear(nombre);
//		UsuariosHistorico.datos().add(usuario);
//		return usuario;
//	}
//
//	public static Usuario cargar(String nombre, String banco, Integer nroCuenta, Double saldo) {
//		Cuenta cuenta = Cuenta.crear(banco, saldo, saldo);
//		Trader usuario = Trader.crear(nombre, cuenta);
//		UsuariosHistorico.datos().add(usuario);
//		return usuario;
//	}
//
//	public static Usuario crear(String nombre) {
//		Usuario usuario = UsuariosHistorico.cargar(nombre);
//		UsuariosHistorico.guardarCSV();
//
//		return usuario;
//	}
//
//	public static Usuario crear(String nombre, String banco, Integer nroCuenta, Double saldo) {
//		Usuario usuario = UsuariosHistorico.cargar(nombre, banco, nroCuenta, saldo);
//		UsuariosHistorico.guardarCSV();
//		return usuario;
//	}
//
//	public static Usuario buscarPorNombre(String nombre) {
//		return UsuariosHistorico.datos().stream().filter(item -> item.nombre.equals(nombre)).findFirst().orElse(null);
//	}
	public static Historico cargar(Trader usuario, String simbolo, Double cantidad) {
		Criptomoneda cripto = Criptomonedas.buscarPorSimbolo(simbolo);
		Historico historico = Historico.crear(cripto, cantidad);
		usuario.agregarHistorico(historico);

		return historico;
	}

	public static Historico crear(Trader usuario, String simbolo, Double cantidad) {
		Historico historico = UsuariosHistorico.cargar(usuario, simbolo, cantidad);
		UsuariosHistorico.guardarCSV(usuario);
		return historico;
	}

	public static void guardarCSV(Trader usuario) {
		List<String> data = usuario.historico.stream().map(item -> item.toCSVString()).toList();
		CSV.guardar(String.format("%s_historico.csv", usuario.nombre), data);
	}

	public static void cargarCSV(Trader usuario) {
		CSV.cargar(String.format("%s_historico.csv", usuario.nombre))
				.forEach(item -> UsuariosHistorico.cargar(usuario, item[0], Double.valueOf(item[1])));
	}

	public static Historico buscarPorCriptomoneda(Trader usuario, Criptomoneda criptomoneda) {
		return usuario.historico.stream().filter(item -> item.criptomoneda.equals(criptomoneda)).findFirst()
				.orElse(null);
	}

	public static void agregar(Trader usuario, Criptomoneda criptomoneda, Double cantidad) {
		Historico historico = UsuariosHistorico.buscarPorCriptomoneda(usuario, criptomoneda);

		if (Helper.esNulo(historico)) {
			historico = UsuariosHistorico.crear(usuario, criptomoneda.getSimbolo(), cantidad);
		} else {
			historico.cantidad += cantidad;
		}
		UsuariosHistorico.guardarCSV(usuario);
	}

	public static void sacar(Trader usuario, Criptomoneda criptomoneda, Double cantidad) {
		Historico historico = UsuariosHistorico.buscarPorCriptomoneda(usuario, criptomoneda);

//		if (Helper.esNulo(historico)) {
//			historico = UsuariosHistorico.crear(usuario, criptomoneda.getSimbolo(), cantidad);
//		} else {
			historico.cantidad -= cantidad;
//		}
		UsuariosHistorico.guardarCSV(usuario);
	}

	
	public static void consultar(Trader usuario) {
		usuario.historico.stream().sorted(Comparator.comparingDouble(Historico::getCantidad).reversed())
				.forEach(item -> Consola.logLn(item.consultar()));
	}
//
//	public static void sacarSaldo(Trader trader, Double monto) {
//		trader.cuenta.actualizar(trader.cuenta.banco, trader.cuenta.numero, trader.cuenta.saldo - monto);
//		UsuariosHistorico.guardarCSV();
//	}
//
//	public static void agregarSaldo(Trader trader, Double monto) {
//		trader.cuenta.actualizar(trader.cuenta.banco, trader.cuenta.numero, trader.cuenta.saldo + monto);
//		UsuariosHistorico.guardarCSV();
//	}
//
//	public static void adios() {
//		Consola.logLn("Hasta luego!");
//	}
}
