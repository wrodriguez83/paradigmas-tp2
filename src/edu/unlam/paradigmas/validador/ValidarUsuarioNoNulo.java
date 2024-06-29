package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInexistenteExcepcion;
import edu.unlam.paradigmas.usuario.Usuario;
import edu.unlam.paradigmas.usuario.Usuarios;

public class ValidarUsuarioNoNulo extends Validador {

    @Override
    public boolean validar(Object valor) {
        Usuario usuario = Usuarios.buscarPorNombre(valor.toString());
        boolean valido = Helper.esNulo(usuario);

        if (!valido) {
            throw new EntidadInexistenteExcepcion("usuario");
        }

        return valido;
    }
}
