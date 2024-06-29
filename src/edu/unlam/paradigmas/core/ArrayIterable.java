package edu.unlam.paradigmas.core;

import java.util.Iterator;

public class ArrayIterable<T> implements Iterable<T> {
	T[] data;

	public ArrayIterable(T[] data) {
		super();
		this.data = data;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator<>(this);
	}
}
