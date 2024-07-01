package edu.unlam.paradigmas.core;

import java.util.Iterator;
import java.util.List;

import edu.unlam.paradigmas.mercado.Criptomoneda;
import edu.unlam.paradigmas.mercado.Mercado;

public class MayorPrecioIterable implements Iterable<Mercado> {
	List<Criptomoneda> data;

	public MayorPrecioIterable(List<Criptomoneda> data) {
		super();
		this.data = data;
	}

	@Override
	public Iterator<Mercado> iterator() {
		return new MayorPrecioIterator(this);
	}
}
