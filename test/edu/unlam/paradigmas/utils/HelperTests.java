package edu.unlam.paradigmas.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.unlam.paradigmas.core.Helper;

class HelperTests {

	@Test
	void DebeSerVacio() {
		assertTrue(Helper.esVacio(""));
		assertTrue(Helper.esVacio("    "));
	}

	@Test
	void NoDebeSerVacio() {
		assertFalse(Helper.esVacio("1"));
		assertFalse(Helper.esVacio(null));
	}

	@Test
	void DebeSerNulo() {
		assertTrue(Helper.esNulo(null));
	}

	@Test
	void NoDebeSerNulo() {
		assertFalse(Helper.esNulo("1"));
		assertFalse(Helper.esNulo(""));
	}
	
	@Test
	void DebeSerNuloOVacio() {
		assertTrue(Helper.esNuloOVacio(null));
		assertTrue(Helper.esNuloOVacio(""));
		assertTrue(Helper.esNuloOVacio("    "));
	}

	@Test
	void NoDebeSerNuloOVacio() {
		assertFalse(Helper.esNuloOVacio("1"));
	}
}
