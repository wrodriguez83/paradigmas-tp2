package edu.unlam.paradigmas.excepcion;

public class EntidadExistenteExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadExistenteExcepcion(String entidad) {
        super(String.format("Se encontró '%s'", entidad));
    }
}
