package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadExistenteExcepcion;

public class ValidarNulo extends Validador {

    private final String entidad;

    public ValidarNulo(String entidad) {
        this.entidad = entidad;
    }

    @Override
    public boolean validar(Object valor) {
        boolean valido = Helper.esNulo(valor);
        if (!valido) {
            throw new EntidadExistenteExcepcion(entidad);
        }

        return valido;
    }

}
