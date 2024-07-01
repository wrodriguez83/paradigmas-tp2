package edu.unlam.paradigmas.wallet;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.core.Helper;
import edu.unlam.paradigmas.entrada.EntradaCriptomoneda;
import edu.unlam.paradigmas.entrada.EntradaDouble;
import edu.unlam.paradigmas.entrada.EntradaMenu;
import edu.unlam.paradigmas.entrada.EntradaSiNo;
import edu.unlam.paradigmas.entrada.EntradaString;
import edu.unlam.paradigmas.excepcion.OperacionCanceladaExcepcion;
import edu.unlam.paradigmas.mercado.Criptomoneda;
import edu.unlam.paradigmas.mercado.Criptomonedas;
import edu.unlam.paradigmas.mercado.Historico;
import edu.unlam.paradigmas.mercado.Mercado;
import edu.unlam.paradigmas.mercado.Mercados;
import edu.unlam.paradigmas.mercado.UsuariosHistorico;
import edu.unlam.paradigmas.usuario.Trader;
import edu.unlam.paradigmas.usuario.Usuarios;
import edu.unlam.paradigmas.validador.Validador;
import edu.unlam.paradigmas.validador.ValidarCriptomonedaNulo;
import edu.unlam.paradigmas.validador.ValidarDoubleMenorIgualA;
import edu.unlam.paradigmas.validador.ValidarDoublePositivo;
import edu.unlam.paradigmas.validador.ValidarDoublePositivoCero;
import edu.unlam.paradigmas.validador.ValidarNoNulo;
import edu.unlam.paradigmas.validador.ValidarString;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WalletCriptomoneda {

    public void crear() {
        Consola.titulo("Crear criptomoneda");

        try {
            String simbolo = new EntradaString().solicitar("Ingrese el simbolo:", new Validador[]{new ValidarString(),
                new ValidarNoNulo("criptomoneda"), new ValidarCriptomonedaNulo()});

            Criptomoneda cripto = Criptomonedas.buscarPorSimbolo(simbolo);

            if (Helper.esNulo(cripto)) {
                String nombre = new EntradaString().solicitar("Ingrese el nombre:", new ValidarString());
                Double precio = new EntradaDouble().solicitar("Ingrese el precio:", new ValidarDoublePositivoCero());

                Criptomonedas.crear(simbolo.toUpperCase(), nombre, precio);

                Consola.info("Criptomoneda creada correctamente!");
            } else {
                boolean quiereModificar = new EntradaSiNo().solicitar("¿Desea modificarla?");

                if (quiereModificar) {
                    menuModificar();
                }
            }
        } catch (OperacionCanceladaExcepcion e) {
            Consola.advertir(e.getMessage());
        }

    }

    public boolean menuModificar() throws OperacionCanceladaExcepcion {
        boolean debeSalir;
        List<String> opciones = new ArrayList<>();

        opciones.add("Modificar símbolo");
        opciones.add("Modificar nombre");
        opciones.add("Modificar precio");
        opciones.add("Volver");

        Integer opcion = new EntradaMenu(opciones).solicitar();
        debeSalir = procesarMenuModificarCripto(opcion);

        while (!debeSalir) {
            debeSalir = menuModificar();
        }

        return debeSalir;
    }

    private boolean procesarMenuModificarCripto(Integer opcion) throws OperacionCanceladaExcepcion {
        switch (opcion) {
            case 1 ->
                modificarSimbolo();
            case 2 ->
                modificarNombre();
            case 3 ->
                modificarPrecio();
            default -> {
                return true;
            }
        }

        return false;
    }

    private void modificarSimbolo() throws OperacionCanceladaExcepcion {
        Criptomoneda cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:",
                new ValidarNoNulo("criptomoneda"));
        String nuevoSimbolo = new EntradaString().solicitar("Ingrese el nuevo simbolo:", new Validador[]{
            new ValidarString(), new ValidarNoNulo("criptomoneda"), new ValidarCriptomonedaNulo()});

        Criptomonedas.modificarSimbolo(cripto, nuevoSimbolo);
        Consola.info("Símbolo modificado correctamente!");
    }

    private void modificarNombre() throws OperacionCanceladaExcepcion {
        Criptomoneda cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:",
                new ValidarNoNulo("criptomoneda"));
        String nuevoNombre = new EntradaString().solicitar("Ingrese el nuevo nombre:", new ValidarString());

        Criptomonedas.modificarNombre(cripto, nuevoNombre);

        Consola.info("Nombre modificado correctamente!");
    }

    private void modificarPrecio() throws OperacionCanceladaExcepcion {
        Criptomoneda cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:",
                new ValidarNoNulo("criptomoneda"));
        Double nuevoPrecio = new EntradaDouble().solicitar("Ingrese el nuevo precio:", new ValidarDoublePositivoCero());
        Criptomonedas.modificarPrecio(cripto, nuevoPrecio);

        Consola.info("Precio modificado correctamente!");
    }

    protected void eliminar() throws OperacionCanceladaExcepcion {
        Criptomoneda cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:",
                new ValidarNoNulo("criptomoneda"));
        Criptomonedas.eliminar(cripto);

        Consola.info("Criptomoneda eliminada correctamente!");
    }

    protected void consultar() throws OperacionCanceladaExcepcion {
        Criptomoneda cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:",
                new ValidarNoNulo("criptomoneda"));
        Consola.info(cripto.getInformacion());
        Consola.titulo("Datos del mercado:");
        Mercados.consultar(cripto);
    }

    protected void comprar(Trader trader) throws OperacionCanceladaExcepcion {
        Consola.titulo("Comprar criptomoneda");

        Criptomoneda cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:",
                new ValidarNoNulo("criptomoneda"));
        Mercado mercado = Mercados.buscarPorCriptomoneda(cripto);

        Consola.info(cripto.getInformacionConCapacidad());
        Double cantidad = new EntradaDouble().solicitar("Ingrese la cantidad a comprar:",
                new Validador[]{new ValidarDoublePositivo(), new ValidarDoubleMenorIgualA(mercado.getCapacidad())});

        Double monto = cripto.calcularTotal(cantidad);

        Consola.logLn(String.format("Total: %-15s", new DecimalFormat("0.####").format(monto)));

        boolean quiereComprar = new EntradaSiNo().solicitar("¿Confirma la compra?");

        if (quiereComprar) {

            boolean puedeComprar = trader.puedeComprar(monto);

            if (puedeComprar) {
                Criptomonedas.comprar(cripto, cantidad);
                UsuariosHistorico.agregar(trader, cripto, cantidad);
                Usuarios.sacarSaldo(trader, monto);

                Consola.info("Compra realizada con exito!");
            } else {
                Consola.advertir("El usuario no tiene saldo para la operación");
                Double diferenciaSaldo = trader.getDiferenciaSaldo(monto);
                boolean agregarSaldo = new EntradaSiNo()
                        .solicitar(String.format("¿Desea agregar %s al saldo del usuario?",
                                new DecimalFormat("0.####").format(diferenciaSaldo)));

                if (agregarSaldo) {
                    Usuarios.agregarSaldo(trader, diferenciaSaldo);
                }
            }
        }
    }

    protected void vender(Trader trader) throws OperacionCanceladaExcepcion {
        Consola.titulo("Vender criptomoneda");

        Criptomoneda cripto = new EntradaCriptomoneda().solicitar("Ingrese el simbolo:",
                new ValidarNoNulo("criptomoneda"));
        Historico historico = UsuariosHistorico.buscarPorCriptomoneda(trader, cripto);

        if (Helper.esNulo(historico)) {
            Consola.advertir("El usuario no posee la criptomoneda");
            throw new OperacionCanceladaExcepcion();
        }
        Consola.info(historico.getInformacionConCapacidad());
        Double cantidad = new EntradaDouble().solicitar("Ingrese la cantidad a comprar:",
                new Validador[]{new ValidarDoublePositivo(), new ValidarDoubleMenorIgualA(historico.getCantidad())});

        Double monto = cripto.calcularTotal(cantidad);

        Consola.logLn(String.format("Total: %-15s", new DecimalFormat("0.####").format(monto)));

        boolean quiereComprar = new EntradaSiNo().solicitar("¿Confirma la compra?");

        if (quiereComprar) {

//			boolean puedeComprar = trader.puedeComprar(monto);
//			if (puedeComprar) {
            Mercados.vender(cripto, cantidad);
            UsuariosHistorico.sacar(trader, cripto, cantidad);
            Usuarios.agregarSaldo(trader, monto);

            Consola.info("Venta realizada con exito!");
//			} else {
//				Consola.advertir("El usuario no tiene saldo para la operación");
//				Double diferenciaSaldo = trader.getDiferenciaSaldo(monto);
//				boolean agregarSaldo = new EntradaSiNo()
//						.solicitar(String.format("¿Desea agregar %s al saldo del usuario?",
//								new DecimalFormat("0.####").format(diferenciaSaldo)));
//
//				if (agregarSaldo) {
//					Usuarios.agregarSaldo(trader, diferenciaSaldo);
//				}
//			}
        }
    }

    public void recomendar() {
        Criptomonedas.recomendar();
    }
}
