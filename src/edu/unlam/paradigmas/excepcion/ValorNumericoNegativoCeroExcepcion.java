package edu.unlam.paradigmas.excepcion;

import java.text.DecimalFormat;

public class ValorNumericoNegativoCeroExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValorNumericoNegativoCeroExcepcion(Integer valor) {
        super(String.format("El valor '%s' debe ser mayor a cero.", new DecimalFormat("0.####").format(valor)));
    }

    public ValorNumericoNegativoCeroExcepcion(Double valor) {
        super(String.format("El valor '%s' debe ser mayor a cero.", new DecimalFormat("0.####").format(valor)));
    }
}
