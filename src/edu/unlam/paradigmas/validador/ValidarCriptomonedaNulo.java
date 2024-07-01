package edu.unlam.paradigmas.validador;

import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadExistenteExcepcion;
import edu.unlam.paradigmas.mercado.Criptomoneda;
import edu.unlam.paradigmas.mercado.Criptomonedas;

public class ValidarCriptomonedaNulo extends Validador {

    @Override
    public boolean validar(Object valor) {
        Criptomoneda cripto = Criptomonedas.buscarPorSimbolo(valor.toString());
        boolean valido = Helper.esNulo(cripto);

        if (!valido) {
            throw new EntidadExistenteExcepcion("criptomoneda");
        }

        return valido;
    }
}
