package juego.partidas;

import juego.core.MenuPrincipal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JugadorHumano extends Jugador {

    //Atributos
    Scanner scanner = new Scanner(System.in);

    //Constructor
    public JugadorHumano(String nombre) {
        setNombre(nombre);
    }

    //Métodos heredados
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
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        return respuestaUsuario;
    }

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
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        return respuestaUsuario;
    }

    public int contestarIngles(int opcionCorrecta) {
        opcionCorrecta = 0;
        boolean respuestaValida = false;

        while (!respuestaValida) {
            try {
                System.out.print("- Tu respuesta: ");
                opcionCorrecta = MenuPrincipal.insertarOpcion(1,2);
                respuestaValida = true;

            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        return opcionCorrecta;
    }
}

