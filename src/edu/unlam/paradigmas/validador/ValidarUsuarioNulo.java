package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadExistenteExcepcion;
import edu.unlam.paradigmas.usuario.Usuario;
import edu.unlam.paradigmas.usuario.Usuarios;

public class ValidarUsuarioNulo extends Validador {

    @Override
    public boolean validar(Object valor) {
        Usuario usuario = Usuarios.buscarPorNombre(valor.toString());
        boolean valido = Helper.esNulo(usuario);

        if (!valido) {
            throw new EntidadExistenteExcepcion("usuario");
        }

        return valido;
    }
}
