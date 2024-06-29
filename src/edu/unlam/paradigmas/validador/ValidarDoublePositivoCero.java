package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.excepcion.ValorNumericoNegativoExcepcion;

public class ValidarDoublePositivoCero extends Validador {

    @Override
    public boolean validar(Object valor) {
        boolean valido = (Double) valor >= 0;
        if (!valido) {
            throw new ValorNumericoNegativoExcepcion((Double) valor);
        }

        return valido;
    }
}
