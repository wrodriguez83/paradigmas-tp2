package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.excepcion.ValorNumericoNegativoCeroExcepcion;

public class ValidarDoublePositivo extends Validador {
    @Override
    public boolean validar(Object valor) {
        boolean valido = (Double) valor > 0;
        if (!valido) {
            throw new ValorNumericoNegativoCeroExcepcion((Double) valor);
        }

        return valido;
    }
}
