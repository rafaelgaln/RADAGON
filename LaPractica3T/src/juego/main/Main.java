package juego.main;
import juego.core.MenuPrincipal;
import juego.core.MenuUsuario;
import juego.utilidades.Constantes;
import juego.utilidades.GestionFicheros;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GestionFicheros.crearFichero(Constantes.rutaFicheroUsuarios, Constantes.nombreFicheroUsuarios);

        while (MenuPrincipal.isEstadoMenu()) {
            switch (MenuPrincipal.mostrarMenuPrincipal()) {
                case 2:
                    MenuPrincipal.mostrarRanking();
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
