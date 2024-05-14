package juego.main;

import juego.partidas.Cpu;
import juego.partidas.JugadorHumano;
import juego.utilidades.GestionPreguntas;

public class MainTest {

    public static void main(String[] args) {

        JugadorHumano jugador= new JugadorHumano();
        Cpu cpu = new Cpu();

        for (int i = 0; i<5; i++) {
            GestionPreguntas.preguntaAleatoria(jugador);
            GestionPreguntas.preguntaAleatoria(cpu);
        }

    }
}
