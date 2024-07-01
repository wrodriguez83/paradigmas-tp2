package edu.unlam.paradigmas.core;

import java.util.Iterator;
import java.util.Objects;

import edu.unlam.paradigmas.mercado.Mercado;

public class MayorPrecioIterator implements Iterator<Mercado> {
	private int indice;
	private final MayorPrecioIterable iterable;
	private Double precioMaximo;

	public MayorPrecioIterator(MayorPrecioIterable iterable) {
		this.iterable = iterable;
	}

	@Override
	public boolean hasNext() {
		if (iterable.data == null) {
			return false;
		}

		if (Objects.equals(precioMaximo, iterable.data.get(indice).getPrecio())) {
			return true;
		} else if (precioMaximo < iterable.data.get(indice).getPrecio()) {
			precioMaximo = iterable.data.get(indice).getPrecio();
			return true;
		} else {
			while (indice < iterable.data.size()) {
				if (precioMaximo > iterable.data.get(indice).getPrecio()) {
					indice++;
				}
			}
		}

		return indice < iterable.data.size();
	}

	@Override
	public Mercado next() {
		if (iterable.data == null) {
			return null;
		}
Mercado mercado = Mercado.
		
		T elemento = iterable.data[index];
		index++;

		return elemento;
	}
}
