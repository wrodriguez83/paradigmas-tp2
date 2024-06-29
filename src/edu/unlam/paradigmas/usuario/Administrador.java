package edu.unlam.paradigmas.usuario;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;

public class Administrador extends Usuario {

    protected Administrador(String nombre) throws EntidadInvalidaExcepcion {
        super(nombre);
    }

    @Override
    public String toCSVString() {
        return String.format("administrador,%s", this.nombre);
    }

    @Override
    public boolean esAdministrador() {
        return true;
    }

    @Override
    public boolean esTrader() {
        return false;
    }

    protected static Administrador crear(String nombre) {
        try {
            Administrador entidad = new Administrador(nombre);

            return entidad;
        } catch (EntidadInvalidaExcepcion e) {
            Consola.error(e.getMessage());
        }

        return null;
    }

    @Override
    public void mostrarBienvenida() {
        Consola.titulo(String.format("Bienvenido %s! (Administrador)", nombre));
    }
}
