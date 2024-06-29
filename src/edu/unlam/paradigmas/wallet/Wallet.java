package edu.unlam.paradigmas.wallet;

import edu.unlam.paradigmas.core.Consola;
import edu.unlam.paradigmas.entrada.EntradaDouble;
import edu.unlam.paradigmas.entrada.EntradaInteger;
import edu.unlam.paradigmas.entrada.EntradaMenu;
import edu.unlam.paradigmas.entrada.EntradaString;
import edu.unlam.paradigmas.entrada.EntradaUsuario;
import edu.unlam.paradigmas.excepcion.OperacionCanceladaExcepcion;
import edu.unlam.paradigmas.moneda.Criptomonedas;
import edu.unlam.paradigmas.moneda.Mercados;
import edu.unlam.paradigmas.usuario.Trader;
import edu.unlam.paradigmas.usuario.Usuario;
import edu.unlam.paradigmas.usuario.Usuarios;
import edu.unlam.paradigmas.usuario.UsuariosHistorico;
import edu.unlam.paradigmas.validador.Validador;
import edu.unlam.paradigmas.validador.ValidarDoublePositivoCero;
import edu.unlam.paradigmas.validador.ValidarIntegerPositivo;
import edu.unlam.paradigmas.validador.ValidarNoNulo;
import edu.unlam.paradigmas.validador.ValidarNulo;
import edu.unlam.paradigmas.validador.ValidarString;
import edu.unlam.paradigmas.validador.ValidarUsuarioNulo;
import java.util.ArrayList;
import java.util.List;

public class Wallet {

    private Usuario usuario;
    private final WalletCriptomoneda wCriptomoneda = new WalletCriptomoneda();

    public void ingresar() {
        Usuarios.cargarCSV();
        Criptomonedas.cargarCSV();
        Mercados.cargarCSV();
        autenticar();
        mostrarMenu();
    }

    private void autenticar() {
        try {
            usuario = new EntradaUsuario().solicitar("Ingrese el nombre del usuario:", new ValidarNoNulo("usuario"));
            if (usuario == null) {
                Consola.advertir("Usuario inexistente");
                usuario = crearUsuario();
            }

            if (usuario.esTrader()) {
                UsuariosHistorico.cargarCSV((Trader) usuario);
            }

            usuario.mostrarBienvenida();
        } catch (OperacionCanceladaExcepcion e) {
            Consola.advertir(e.getMessage());
            salir();
        }
    }

    private void mostrarMenu() {
        try {
            if (usuario.esAdministrador()) {
                menuAdministrador();
            } else {
                menuTrader();
            }
        } catch (OperacionCanceladaExcepcion e) {
            Consola.advertir(e.getMessage());
            salir();
        }
    }

    private boolean menuAdministrador() throws OperacionCanceladaExcepcion {

        boolean debeSalir;
        List<String> opciones = new ArrayList<>();

        opciones.add("Crear criptomoneda");
        opciones.add("Modificar Criptomoneda");
        opciones.add("Eliminar Criptomoneda");
        opciones.add("Consultar Criptomoneda");
        opciones.add("Consultar estado actual del mercado");
        opciones.add("Salir");

        Integer opcion = new EntradaMenu(opciones).solicitar();
        debeSalir = procesarMenuAdministrador(opcion);

        while (!debeSalir) {
            debeSalir = menuAdministrador();
        }

        return debeSalir;

    }

    private boolean menuTrader() throws OperacionCanceladaExcepcion {
        boolean debeSalir;
        List<String> opciones = new ArrayList<>();

        opciones.add("Comprar Criptomonedas");
        opciones.add("Vender Criptomonedas");
        opciones.add("Consultar Criptomoneda");
        opciones.add("Recomendar Criptomonedas.");
        opciones.add("Consultar estado actual del mercado");
        opciones.add("Visualizar archivo de transacciones (histórico).");
        opciones.add("Salir");

        Integer opcion = new EntradaMenu(opciones).solicitar();
        debeSalir = procesarMenuTrader(opcion);

        while (!debeSalir) {
            debeSalir = menuTrader();
        }

        return debeSalir;
    }

    private boolean procesarMenuAdministrador(Integer opcion) {
        try {
            switch (opcion) {
                case 1 ->
                    wCriptomoneda.crear();
                case 2 ->
                    wCriptomoneda.menuModificar();
                case 3 ->
                    wCriptomoneda.eliminar();
                case 4 ->
                    wCriptomoneda.consultar();
                case 5 ->
                    Mercados.consultar();
                default -> {
                    salir();
                    return true;
                }
            }
        } catch (OperacionCanceladaExcepcion e) {
            Consola.advertir(e.getMessage());
        }

        return false;
    }

    private boolean procesarMenuTrader(Integer opcion) {
        try {
            switch (opcion) {
                case 1 ->
                    wCriptomoneda.comprar((Trader) usuario);
                case 2 ->
                    wCriptomoneda.vender((Trader) usuario);
                case 3 ->
                    wCriptomoneda.consultar();
                case 4 ->
                    wCriptomoneda.recomendar();
                case 5 ->
                    Mercados.consultar();
                case 6 ->
                    UsuariosHistorico.consultar((Trader) usuario);
//			} else if (opcion == 2) {
//				Criptomonedas.menuModificarCripto();
//			} else if (opcion == 3) {
//				Criptomonedas.eliminarPorConsola();
//			} else if (opcion == 4) {
//				Criptomonedas.consultarCriptomoneda();
                default -> {
                    salir();
                    return true;
                }
            }
        } catch (OperacionCanceladaExcepcion e) {
            Consola.advertir(e.getMessage());
        }

        return false;
    }

    private Usuario crearUsuario() throws OperacionCanceladaExcepcion {
        Consola.titulo("Crear usuario");

        String nombre = new EntradaString().solicitar("Ingrese el nombre del usuario:",
                new Validador[]{new ValidarString(), new ValidarNulo("usuario"), new ValidarUsuarioNulo()});
        String banco = new EntradaString().solicitar("Ingrese el banco:", new ValidarString());
        Integer nroCuenta = new EntradaInteger().solicitar("Ingrese el número de cuenta:",
                new ValidarIntegerPositivo());
        Double saldo = new EntradaDouble().solicitar("Ingrese el saldo:", new ValidarDoublePositivoCero());

        usuario = Usuarios.crear(nombre, banco, nroCuenta, saldo);
        Usuarios.guardarCSV();

        Consola.info("Usuario creado correctamente!");

        return usuario;
    }

    private void salir() {
        Usuarios.adios();
    }
}
