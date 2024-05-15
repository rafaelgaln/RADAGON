package juego.main;
import juego.core.MenuPrincipal;
import juego.core.MenuUsuario;
import juego.partidas.Partida;
import juego.utilidades.Constantes;
import juego.utilidades.GestionFicheros;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Gestionar Ficheros antes de empezar la base del programa
        GestionFicheros.crearDirectorio(Constantes.nombreDirectorioFicheros);
        GestionFicheros.crearDirectorio(Constantes.rutaDirectorioLogs);

        GestionFicheros.crearFichero(Constantes.rutaFicheroUsuarios, Constantes.nombreFicheroUsuarios);
        GestionFicheros.crearFichero(Constantes.rutaFicheroHistorico, Constantes.nombreFicheroHistorico);

        GestionFicheros.checkFichero(Constantes.rutaFicheroDiccionario, Constantes.nombreFicheroDiccionario);
        GestionFicheros.checkFichero(Constantes.rutaFicheroIngles, Constantes.nombreFicheroIngles);

        while (MenuPrincipal.isEstadoMenu()) {
            switch (MenuPrincipal.mostrarMenuPrincipal()) {
                case 1:
                    Partida partida = new Partida();
                    partida.bienvenida();
                    partida.tamanioPartida();
                    partida.escogerJugadores();
                    partida.jugarPartida();
                    partida.finPartida();
                    break;
                case 2:
                    MenuPrincipal.mostrarRanking();
                    break;
                case 3:
                    MenuPrincipal.mostrarHistorico();
                    break;
                case 4:
                    MenuUsuario.abrirMenuUsuario();
                    break;
                case 5:
                    MenuPrincipal.setEstadoMenu(false);
                    break;
            }
        }
    }
}
