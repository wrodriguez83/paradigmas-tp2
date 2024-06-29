package edu.unlam.paradigmas.entrada;

import edu.unlam.paradigmas.usuario.Usuario;
import edu.unlam.paradigmas.usuario.Usuarios;
import edu.unlam.paradigmas.validador.ValidarString;

public class EntradaUsuario extends Entrada<Usuario> {
	@Override
	protected Usuario convertir(String valor) {
		boolean valido = new ValidarString().validar(valor);

		if (valido) {
			return Usuarios.buscarPorNombre(valor);
		}

		return null;
	}
}
