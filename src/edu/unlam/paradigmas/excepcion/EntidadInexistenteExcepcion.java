package edu.unlam.paradigmas.excepcion;

public class EntidadInexistenteExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadInexistenteExcepcion(String entidad) {
        super(String.format("No se encuentra '%s'", entidad));
    }
}
