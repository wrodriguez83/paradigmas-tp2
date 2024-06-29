package edu.unlam.paradigmas.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.core.CSV;
import edu.unlam.paradigmas.test.ConsolaMock;

class CSVTests extends ConsolaMock {
	String archivo = "test.csv";

	@AfterEach
	public void AfterEach() {
		super.AfterEach();
		try {
			Files.deleteIfExists(Path.of(archivo));
		} catch (IOException e) {
		}
	}

	@Test
	void NoDebeMostrarErrorSiElArchivoNoExisteAlCargar() {
		CSV.cargar("testtest/test.csv");
		assertEquals("", getOutput());
	}

	@Test
	void DebePoderCargar() throws IOException {
		FileWriter writer = new FileWriter(archivo);
		writer.write("dato prueba,otro,otro mas\naca hay otro,si otro,123\n123.4");
		writer.close();

		List<String[]> datos = CSV.cargar(archivo);

		assertEquals("dato prueba", datos.get(0)[0]);
		assertEquals("otro", datos.get(0)[1]);
		assertEquals("otro mas", datos.get(0)[2]);

		assertEquals("aca hay otro", datos.get(1)[0]);
		assertEquals("si otro", datos.get(1)[1]);
		assertEquals("123", datos.get(1)[2]);

		assertEquals("123.4", datos.get(2)[0]);
	}

	@Test
	void DebeTirarExcepcionSiElArchivoNoExisteAlGuardar() {
		CSV.guardar("testtest/test.csv", Arrays.asList());
		assertEquals("!! ERROR: testtest/test.csv (No such file or directory)", getOutput());
	}

	@Test
	void DebePoderGuardar() throws IOException {
		List<String> datos = new ArrayList<String>();
		datos.add("123.4");
		datos.add("aca hay otro,si otro,123");
		datos.add("dato prueba,otro,otro mas");

		CSV.guardar(archivo, datos);
		List<String[]> csvData = CSV.cargar(archivo);

		assertEquals("123.4", csvData.get(0)[0]);

		assertEquals("aca hay otro", csvData.get(1)[0]);
		assertEquals("si otro", csvData.get(1)[1]);
		assertEquals("123", csvData.get(1)[2]);

		assertEquals("dato prueba", csvData.get(2)[0]);
		assertEquals("otro", csvData.get(2)[1]);
		assertEquals("otro mas", csvData.get(2)[2]);
	}
}
