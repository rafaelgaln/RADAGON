package juego.main;

import juego.core.MenuPrincipal;
import juego.partidas.Jugador;
import juego.partidas.Partida;

public class MainTest {

    public static void main(String[] args) {

        Partida partida = new Partida();
        partida.escogerJugadores();

        for (Jugador jugador : partida.getListaJugadores()) {
            System.out.println(jugador.getNombre());
        }


    }
}
