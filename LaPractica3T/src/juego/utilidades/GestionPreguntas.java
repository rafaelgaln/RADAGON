package juego.utilidades;

import juego.partidas.Jugador;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public abstract class GestionPreguntas {

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
            GestionLogs.escribirLog(GestionLogs.logException(e));
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

    public static int elegirCuestion() {

        List<String> inglesLineas = new ArrayList<String>();

        try {
            inglesLineas = Files.readAllLines(Paths.get(Constantes.rutaFicheroIngles));
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            throw new RuntimeException(e);
        }

        int lineaPregunta = (int) (Math.random() * 1000) * 5;

        List<String> respuestas = new ArrayList<String>();
        for (int i = 1; i <= 4; i++) {
            respuestas.add(inglesLineas.get(lineaPregunta + i));
        }
        String respuestaCorrecta = respuestas.get(0);
        Collections.shuffle(respuestas);
        int indiceRespuestaCorrecta = respuestas.indexOf(respuestaCorrecta) + 1;

        System.out.println("Contesta a esta pregunta en inglés: ");
        System.out.println(inglesLineas.get(lineaPregunta));
        for (int i = 0; i < 4; i++) {
            System.out.print((i + 1) + ") ");
            System.out.println(respuestas.get(i));
            ;
        }

        return indiceRespuestaCorrecta;
    }

    public static boolean preguntaMates(Jugador jugador) {

        String stringOperacion = generarOperacion();

        System.out.println("Resuelve esta operación matemática: " + stringOperacion);
        try {

            Expression operacion = new ExpressionBuilder(stringOperacion).build();
            double resultado = operacion.evaluate();
            double respuestaUsuario = jugador.contestarMates(resultado);

            if (resultado == respuestaUsuario) {
                System.out.println("¡Respuesta correcta! :D");
            } else {
                System.out.println("¡No! La respuesta era: " + resultado);
                return false;
            }
        } catch (Exception e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: Tipo de dato insertado erroneo: " + e.getMessage() + "\n" +
                    "Respuesta evaluada como erronea.");
            return false;
        }
        return true;
    }

    public static boolean preguntaLengua(Jugador jugador) {

        String palabra = palabraAleatoria();
        System.out.println("Completa con las letras que le faltan a la palabra: " + ocultarPalabra(palabra));

        try {
            String respuestaUsuario = jugador.contestarLengua(palabra);

            if (Objects.equals(respuestaUsuario, palabra)) {
                System.out.println("¡Respuesta correcta! :D");
            } else {
                System.out.println("¡No! La respuesta era: " + palabra);
                return false;
            }
        } catch (Exception e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: Tipo de dato insertado erroneo: " + e.getMessage() + "\n" +
                    "Respuesta evaluada como erronea.");
            return false;
        }
        return true;
    }

    public static boolean preguntaIngles(Jugador jugador) {

        //Nota: elegirCuestion() imprime la pregunta
        int opcionCorrecta = elegirCuestion();
        try {
            int respuestaUsuario = jugador.contestarIngles(opcionCorrecta);

            if (respuestaUsuario == opcionCorrecta) {
                System.out.println("¡Respuesta correcta! :D");
            } else {
                System.out.println("¡No! La respuesta era: " + opcionCorrecta);
                return false;
            }
        } catch (Exception e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: Tipo de dato insertado erroneo: " + e.getMessage() + "\n" +
                    "Respuesta evaluada como erronea.");
            return false;
        }

        return true;
    }

    public static boolean preguntaAleatoria(Jugador jugador) {
        int numeroAleatorio = (int) (Math.random()*3)+1;
        boolean preguntaAcertada = false;
        switch (numeroAleatorio) {
            case 1:
                preguntaAcertada = preguntaMates(jugador);
                break;
            case 2:
                preguntaAcertada = preguntaLengua(jugador);
                break;
            case 3:
                preguntaAcertada = preguntaIngles(jugador);
                break;
        }
        return preguntaAcertada;
    }
}
