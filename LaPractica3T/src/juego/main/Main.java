package juego.main;
import juego.core.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        while (MenuPrincipal.getEstadoPrograma()) {
            switch (MenuPrincipal.mostrarMenuPrincipal()) {
                case 5:
                    MenuPrincipal.apagarPrograma();
                    break;
            }
        }
    }
}
