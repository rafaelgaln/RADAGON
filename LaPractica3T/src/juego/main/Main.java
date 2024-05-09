package juego.main;
import juego.core.MenuPrincipal;
import juego.core.MenuUsuario;

public class Main {

    public static void main(String[] args) {

        while (MenuPrincipal.isEstadoMenu()) {
            switch (MenuPrincipal.mostrarMenuPrincipal()) {
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
