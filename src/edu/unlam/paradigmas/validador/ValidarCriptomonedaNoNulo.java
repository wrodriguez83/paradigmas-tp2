package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInexistenteExcepcion;
import edu.unlam.paradigmas.mercado.Criptomoneda;
import edu.unlam.paradigmas.mercado.Criptomonedas;

public class ValidarCriptomonedaNoNulo extends Validador {

    @Override
    public boolean validar(Object valor) {
        Criptomoneda cripto = Criptomonedas.buscarPorSimbolo(valor.toString());
        boolean valido = Helper.esNulo(cripto);

        if (!valido) {
            throw new EntidadInexistenteExcepcion("criptomoneda");
        }

        return valido;
    }
}
