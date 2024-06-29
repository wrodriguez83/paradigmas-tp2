package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.excepcion.ValorNumericoNegativoCeroExcepcion;

public class ValidarIntegerPositivo extends Validador {

    @Override
    public boolean validar(Object valor) {
        boolean valido = (Integer) valor > 0;
        if (!valido) {
            throw new ValorNumericoNegativoCeroExcepcion((Integer) valor);
        }

        return valido;
    }
}
