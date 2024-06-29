package edu.unlam.paradigmas.entrada;

import edu.unlam.paradigmas.excepcion.OperacionCanceladaExcepcion;
import edu.unlam.paradigmas.validador.Validador;

public class EntradaSiNo extends EntradaBoolean {
	public Boolean solicitar(String mensaje) throws OperacionCanceladaExcepcion {
		Validador[] validadores = new Validador[0];
		return super.solicitar(String.format("%s (s/n/Si/No): ", mensaje), validadores);
	}
}
