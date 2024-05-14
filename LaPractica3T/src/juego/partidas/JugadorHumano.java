package juego.partidas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JugadorHumano extends Jugador {

    //Atributos
    Scanner scanner = new Scanner(System.in);

    //Constructor
    public JugadorHumano(String nombre) {
        this.nombre = nombre;
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
                opcionCorrecta = scanner.nextInt();
                if (opcionCorrecta >= 1 && opcionCorrecta <= 4) {
                    respuestaValida = true;
                } else {
                    System.out.println("Error: Debes ingresar un número del 1 al 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        return opcionCorrecta;
    }
}

