package edu.unlam.paradigmas.entrada;

public class EntradaString extends Entrada<String> {
	@Override
	protected String convertir(String valor) {
		return valor;
	}
}
