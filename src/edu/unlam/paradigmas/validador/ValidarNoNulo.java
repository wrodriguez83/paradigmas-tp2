package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInexistenteExcepcion;

public class ValidarNoNulo extends Validador {

    private final String entidad;

    public ValidarNoNulo(String entidad) {
        this.entidad = entidad;
    }

    @Override
    public boolean validar(Object valor) {
        boolean valido = !Helper.esNulo(valor);
        if (!valido) {
            throw new EntidadInexistenteExcepcion(entidad);
        }

        return valido;
    }

}
