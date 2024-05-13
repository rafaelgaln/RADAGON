package juego.main;

import juego.utilidades.Constantes;
import juego.utilidades.GestionFicheros;
import juego.utilidades.GestionPreguntas;

import java.io.IOException;

import static juego.utilidades.GestionPreguntas.elegirCuestion;

public class MainTest {

    public static void main(String[] args) {
        char correctAnswer = GestionPreguntas.elegirCuestion();
        System.out.println("La respuesta correcta es la opción: " + correctAnswer);
    }
}
