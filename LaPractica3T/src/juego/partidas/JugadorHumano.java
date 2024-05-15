package juego.partidas;

import juego.core.MenuPrincipal;
import juego.utilidades.GestionLogs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Rafael Galán López
 * @since 1.0
 * @version 1.0
 * @see Jugador
 * @see Cpu
 * Clase INSTANCIABLE hija de Jugador. Estas instancias se usan para representar las acciones de los humanos
 * y como se comportan a la hora de responder
 */
public class JugadorHumano extends Jugador {

    //Atributos
    /**
     * Scanner de clase para contestar a las preguntas
     */
    Scanner scanner = new Scanner(System.in);

    //Constructor

    /**
     * Constructor para instanciar Jugadores Humanos.
     * @param nombre Nombre del jugador, registrado en "usuarios.csv"
     */
    public JugadorHumano(String nombre) {
        setNombre(nombre);
        setPuntos(0);
    }

    //Métodos heredados

    /**
     * Método para contestar una pregunta de Mates manualmente con scanner.
     * @param respuestaCorrecta Nota: No se usa para JugadorHumano
     * @return Respuesta del jugador humano
     * @since 1.0
     */
    @Override
    public double contestarMates(double respuestaCorrecta) {

        int respuestaUsuario = 0;
        boolean respuestaValida = false;

        while (!respuestaValida) {
            try {
                System.out.print("- Tu respuesta: ");
                respuestaUsuario = scanner.nextInt();
                respuestaValida = true;
            } catch (InputMismatchException e) {
                GestionLogs.escribirLog(GestionLogs.logException(e));
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        return respuestaUsuario;
    }

    /**
     * Método para contestar una pregunta de Lengua manualmente con scanner.
     * @param palabraCorrecta Nota: No se usa para JugadorHumano
     * @return Respuesta del jugador humano
     * @since 1.0
     */
    @Override
    public String contestarLengua(String palabraCorrecta) {

        String respuestaUsuario = null;
        boolean respuestaValida = false;

        while (!respuestaValida) {
            try {
                System.out.print("- Tu respuesta: ");
                respuestaUsuario = scanner.nextLine();
                respuestaValida = true;
            } catch (InputMismatchException e) {
                GestionLogs.escribirLog(GestionLogs.logException(e));
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        return respuestaUsuario;
    }

    /**
     * Método para contestar una pregunta de Lengua manualmente con scanner.
     * @param opcionCorrecta Nota: No se usa para JugadorHumano
     * @return Respuesta del jugador humano
     * @since 1.0
     */
    @Override
    public int contestarIngles(int opcionCorrecta) {
        opcionCorrecta = 0;
        boolean respuestaValida = false;

        while (!respuestaValida) {
            try {
                System.out.print("- Tu respuesta: ");
                opcionCorrecta = MenuPrincipal.insertarOpcion(1,4);
                respuestaValida = true;

            } catch (InputMismatchException e) {
                GestionLogs.escribirLog(GestionLogs.logException(e));
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        return opcionCorrecta;
    }
}

