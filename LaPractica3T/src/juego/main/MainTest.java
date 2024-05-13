package juego.main;

import juego.utilidades.Constantes;
import juego.utilidades.GestionFicheros;
import juego.utilidades.GestionPreguntas;

import java.io.IOException;

public class MainTest {

    public static void main(String[] args) {


        boolean correcto1 = GestionPreguntas.preguntaLengua(GestionPreguntas.palabraAleatoria());
        boolean correcto2 = GestionPreguntas.preguntaMates(GestionPreguntas.generarOperacion());
        boolean correcto3 = GestionPreguntas.preguntaIngles(GestionPreguntas.elegirCuestion());

    }
}
