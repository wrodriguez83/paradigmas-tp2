package edu.unlam.paradigmas.entrada;

import edu.unlam.paradigmas.moneda.Criptomoneda;
import edu.unlam.paradigmas.moneda.Criptomonedas;
import edu.unlam.paradigmas.validador.ValidarString;

public class EntradaCriptomoneda extends Entrada<Criptomoneda> {
	@Override
	protected Criptomoneda convertir(String valor) {
		boolean valido = new ValidarString().validar(valor);

		if (valido) {
			return Criptomonedas.buscarPorSimbolo(valor);
		}

		return null;
	}
}
