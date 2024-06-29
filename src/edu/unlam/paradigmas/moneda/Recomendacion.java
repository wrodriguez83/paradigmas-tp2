package edu.unlam.paradigmas.moneda;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.excepcion.EntidadInvalidaExcepcion;
import java.text.DecimalFormat;

public class Recomendacion {

    protected Criptomoneda criptomoneda;
    protected Double coeficiente;

    public Recomendacion(Criptomoneda criptomoneda, Double coeficiente) throws EntidadInvalidaExcepcion {
        if (Helper.esNuloOVacio(criptomoneda)) {
            throw new EntidadInvalidaExcepcion("Recomendacion");
        }
        this.criptomoneda = criptomoneda;
        this.coeficiente = coeficiente;

    }

    public String getInformacion() {
        return String.format("Símbolo: %-10s Nombre: %-15s Precio en dólares: %-10s", criptomoneda.simbolo, criptomoneda.nombre,
                new DecimalFormat("0.####").format(criptomoneda.precio));
    }

    protected static Recomendacion crear(Criptomoneda criptomoneda, Double coeficiente) {
        try {
            Recomendacion entidad = new Recomendacion(criptomoneda, coeficiente);

            return entidad;
        } catch (EntidadInvalidaExcepcion e) {
            Consola.error(e.getMessage());
        }

        return null;
    }
}
