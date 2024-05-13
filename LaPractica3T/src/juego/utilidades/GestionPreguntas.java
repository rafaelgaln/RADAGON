package juego.utilidades;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GestionPreguntas {

    private static final Scanner scanner = new Scanner(System.in);

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

    public static String palabraAleatoria() {
        List<String> palabras = new ArrayList<String>();

        try {
            palabras = Files.readAllLines(Paths.get(Constantes.rutaFicheroDiccionario));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int numeroPalabra = (int) (Math.random() * (palabras.size()));

        return palabras.get(numeroPalabra);
    }

    public static String ocultarPalabra(String palabra) {

        int numeroLetras = palabra.length();
        int letrasOcultadas = numeroLetras / 3;

        char[] caracteresPalabra = palabra.toCharArray();

        for (int i = 0; i < letrasOcultadas; i++) {
            int numCaracter = (int) (Math.random() * (numeroLetras));
            while (caracteresPalabra[numCaracter] == '_') {
                numCaracter = (int) (Math.random() * (numeroLetras));
            }
            caracteresPalabra[numCaracter] = '_';
        }
        String palabraOculta = new String(caracteresPalabra);

        return palabraOculta;
    }

    public static boolean preguntaMates(String stringOperacion) {

        System.out.println("Resuelve esta operación matemática: " + stringOperacion);
        try {
            double respuestaUsuario = scanner.nextDouble();
            Expression operacion = new ExpressionBuilder(stringOperacion).build();
            double resultado = operacion.evaluate();

            if (resultado == respuestaUsuario) {
                System.out.println("¡Respuesta correcta! :D");
            } else {
                System.out.println("¡No! La respuesta era: " + resultado);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: Tipo de dato insertado erroneo: " + e.getMessage() + "\n" +
                    "Respuesta evaluada como erronea.");
        } finally {
            scanner.nextLine();
        }
        return true;
    }

    public static boolean preguntaLengua(String palabra) {

        System.out.println("Completa con las letras que le faltan a la palabra: " + ocultarPalabra(palabra));

        try {
            String respuestaUsuario = scanner.nextLine();

            if (Objects.equals(respuestaUsuario, palabra)) {
                System.out.println("¡Respuesta correcta! :D");
            } else {
                System.out.println("¡No! La respuesta era: " + palabra);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: Tipo de dato insertado erroneo: " + e.getMessage() + "\n" +
                    "Respuesta evaluada como erronea.");
        }
        return true;
    }

}
