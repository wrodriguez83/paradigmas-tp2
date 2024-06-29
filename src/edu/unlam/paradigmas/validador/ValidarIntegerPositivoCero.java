package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.excepcion.ValorNumericoNegativoExcepcion;

public class ValidarIntegerPositivoCero extends Validador {

    @Override
    public boolean validar(Object valor) {
        boolean valido = (Integer) valor >= 0;
        if (!valido) {
            throw new ValorNumericoNegativoExcepcion((Integer) valor);
        }

        return valido;
    }
}
