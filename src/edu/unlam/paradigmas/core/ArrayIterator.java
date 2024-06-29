package edu.unlam.paradigmas.core;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
	private int index;
	private final ArrayIterable<T> iterable;

	public ArrayIterator(ArrayIterable<T> iterable) {
		this.iterable = iterable;
	}

	@Override
	public boolean hasNext() {
		if (iterable.data == null) {
			return false;
		}

		return index < iterable.data.length;
	}

	@Override
	public T next() {
		if (iterable.data == null) {
			return null;
		}

		T elemento = iterable.data[index];
		index++;

		return elemento;
	}
}
