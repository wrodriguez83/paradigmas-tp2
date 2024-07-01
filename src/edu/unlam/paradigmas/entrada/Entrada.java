package edu.unlam.paradigmas.entrada;

import edu.unlam.paradigmas.core.ArrayIterable;
import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.excepcion.OperacionCanceladaExcepcion;
import edu.unlam.paradigmas.validador.Validador;

public abstract class Entrada<T> {
	public T solicitar(String mensaje, Validador validador) throws OperacionCanceladaExcepcion {
		Validador[] validadores = new Validador[] { validador };
		return solicitar(mensaje, validadores);
	}

	public T solicitar(String mensaje, Validador[] validadores) throws OperacionCanceladaExcepcion {
		String entrada;
		T entradaConvertida = null;
		boolean entradaValida = false;
		int intentos = 3;
		ArrayIterable<Validador> validadoresIterable = new ArrayIterable<>(validadores);

		do {
			try {
				entrada = Consola.obtener(mensaje);
				entradaConvertida = convertir(entrada);

				for (Validador validador : validadoresIterable) {
//					System.out.println(validador.getClass());
					validador.validar(entradaConvertida);
				}

				entradaValida = true;
			} catch (Exception e) {
				Consola.error(e.getMessage());
				intentos--;
			}

		} while (!entradaValida && intentos > 0);

		if (intentos == 0) {
			throw new OperacionCanceladaExcepcion();
		}

		return entradaConvertida;
	}

	protected abstract T convertir(String valor);
}
