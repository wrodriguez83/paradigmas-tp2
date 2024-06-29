package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.excepcion.ValorInvalidoExcepcion;

public class ValidarString extends Validador {

    @Override
    public boolean validar(Object valor) {
        boolean valido = !valor.toString().contains(",");
        if (!valido) {
            throw new ValorInvalidoExcepcion(",");
        }

        return valido;
    }
}
