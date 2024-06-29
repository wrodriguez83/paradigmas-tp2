package edu.unlam.paradigmas.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import edu.unlam.paradigmas.core.Consola;

public abstract class ConsolaMock {
	protected final PrintStream outStream = System.out;
	protected final InputStream inStream = System.in;
	protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	@BeforeEach
	public void BeforeEach() {
		System.setOut(new PrintStream(outputStream));
	}

	@AfterEach
	protected void AfterEach() {
		System.setOut(outStream);
		Consola.scanner = new Scanner(inStream);
	}

	protected String getOutput() {
		return outputStream.toString().trim();
	}

	protected void resetOutput() {
		outputStream.reset();
	}
}
