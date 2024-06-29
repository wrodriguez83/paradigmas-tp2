package edu.unlam.paradigmas.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSV {
	public static List<String[]> cargar(String archivo) {
		BufferedReader reader;
		List<String[]> lineas = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader(archivo));
			String linea;

			while ((linea = reader.readLine()) != null) {
				lineas.add(linea.split(","));
			}

			reader.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			Consola.error(e.getMessage());
		}

		return lineas;
	}

	public static void guardar(String archivo, List<String> items) {
		String contenido = items.stream().collect(Collectors.joining("\n"));
		FileWriter writer;

		try {
			writer = new FileWriter(archivo);
			writer.write(contenido);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			Consola.error(e.getMessage());
		}
	}
}
