package edu.unlam.paradigmas.excepcion;

import java.text.DecimalFormat;

public class ValorNumericoNegativoExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValorNumericoNegativoExcepcion(Integer valor) {
        super(String.format("El valor '%s' debe ser mayor o igual a cero.", new DecimalFormat("0.####").format(valor)));
    }

    public ValorNumericoNegativoExcepcion(Double valor) {
        super(String.format("El valor '%s' debe ser mayor o igual a cero.", new DecimalFormat("0.####").format(valor)));
    }
}
