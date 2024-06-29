package edu.unlam.paradigmas.core;

import java.util.Scanner;

public class Consola {
	public static Scanner scanner = new Scanner(System.in);

	public static void log(String mensaje) {
		System.out.print(mensaje);
	}

	public static void logLn(String mensaje) {
		System.out.println(mensaje);
	}

	public static void titulo(String mensaje) {
		logLn("");
		logLn(mensaje);
		logLn("-".repeat(mensaje.length()));
	}

	public static void error(String mensaje) {
		logLn("");
		logLn(String.format("!! ERROR: %s", mensaje));
		logLn("");
	}

	public static void advertir(String mensaje) {
		logLn("");
		logLn(mensaje);
		logLn("");
	}

	public static void info(String mensaje) {
		logLn("");
		logLn(String.format("** %s **", mensaje));
		logLn("");
	}

	public static String obtener() {
		return Consola.obtener(null);
	}

	public static String obtener(String mensaje) {
		if (!Helper.esNulo(mensaje)) {
			Consola.log(mensaje);
		}

		return Consola.scanner.nextLine();
	}
}
