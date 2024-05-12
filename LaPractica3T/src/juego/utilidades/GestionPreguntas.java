package juego.utilidades;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class GestionPreguntas {

    public static String generarOperacion() {
        int cantidadNumeros = (int) (Math.random() * 5) + 4;
        int[] arrayNumeros = new int[cantidadNumeros];
        char[] arraySimbolos = new char[cantidadNumeros - 1];

        for (int i = 0; i < arrayNumeros.length; i++) {
            arrayNumeros[i] = (int) (Math.random() * 10) + 1; // Ajustamos el rango de números a 1-10
        }
        for (int i = 0; i < arraySimbolos.length; i++) {
            int tipoSimbolo = (int) (Math.random() * 3); // Ajustamos el rango para incluir la división

            switch (tipoSimbolo) {
                case 0:
                    arraySimbolos[i] = '+';
                    break;
                case 1:
                    arraySimbolos[i] = '-';
                    break;
                case 2:
                    arraySimbolos[i] = '*';
                    break;
            }
        }

        StringBuilder operacionMates = new StringBuilder();
        for (int i = 0; i < cantidadNumeros; i++) {
            operacionMates.append(arrayNumeros[i]);
            if (i < cantidadNumeros - 1) {
                operacionMates.append(" ").append(arraySimbolos[i]).append(" ");
            }
        }
        return operacionMates.toString();
    }

    public static boolean preguntaMates() {
        Scanner scanner = new Scanner(System.in);
        String stringOperacion = generarOperacion();
        System.out.println("Resuelve esta operación matemática: " + stringOperacion);
        try {
            double respuestaUsuario = scanner.nextDouble();
            Expression operacion = new ExpressionBuilder(stringOperacion).build();
            double resultado = operacion.evaluate();

            if (resultado == respuestaUsuario) {
                System.out.println("¡Respuesta correcta! :D");
            } else {
                System.out.println("Respuesta erronea. :(");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: Tipo de dato insertado erroneo: " + e.getMessage() + "\n" +
                    "Respuesta evaluada como erronea.");
        } finally {
            scanner.close();
        }
        return true;
    }
}
