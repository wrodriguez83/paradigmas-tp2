package edu.unlam.paradigmas.excepcion;

public class ValorCSVInvalidoExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValorCSVInvalidoExcepcion(String valor) {
        super(String.format("'%s' no es un valor válido para el formato CSV.", valor));
    }
}
