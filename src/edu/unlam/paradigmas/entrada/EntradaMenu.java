package edu.unlam.paradigmas.entrada;

import java.util.ArrayList;
import java.util.List;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.excepcion.OperacionCanceladaExcepcion;
import edu.unlam.paradigmas.validador.Validador;
import edu.unlam.paradigmas.validador.ValidarIntegerPositivo;
import edu.unlam.paradigmas.validador.ValidarMenu;

public class EntradaMenu extends EntradaInteger {
	private final List<String> opciones = new ArrayList<>();

	public EntradaMenu(List<String> opciones) {
		this.opciones.addAll(opciones);
	}

	public Integer solicitar() throws OperacionCanceladaExcepcion {
		Validador[] validadores = new Validador[] { new ValidarIntegerPositivo(), new ValidarMenu(opciones.size()) };

		return solicitar("Menú de opciones", validadores);
	}

	@Override
	public Integer solicitar(String mensaje, Validador[] validadores) throws OperacionCanceladaExcepcion {
		Consola.titulo(mensaje);

		this.opciones.forEach(opcion -> {
			int indice = this.opciones.indexOf(opcion);
			Consola.logLn(String.format("%d) %s", indice + 1, opcion));
		});

		Integer opcionId = super.solicitar(String.format("\nIngrese su opción (1 - %d): ", this.opciones.size()),
				validadores);

		return opcionId;
	}
}
