package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.excepcion.OpcionMenuInvalidoExcepcion;

public class ValidarMenu extends Validador {

    private Integer cantOpciones = 0;

    public ValidarMenu(Integer cantOpciones) {
        this.cantOpciones = cantOpciones;
    }

    @Override
    public boolean validar(Object valor) {
        boolean valido = (Integer) valor > 0 && (Integer) valor <= cantOpciones;

        if (!valido) {
            throw new OpcionMenuInvalidoExcepcion((Integer) valor);
        }

        return valido;
    }
}
