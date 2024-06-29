package edu.unlam.paradigmas.core;

public class Helper {
	public static boolean esVacio(String valor) {
		return valor != null && valor.trim().length() == 0;
	}

	public static boolean esNulo(Object valor) {
		return valor == null;
	}

	public static boolean esNuloOVacio(Object valor) {
		return Helper.esNulo(valor) || Helper.esVacio(valor.toString());
	}
}
