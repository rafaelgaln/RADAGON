package juego.core;
import java.util.Scanner;

public abstract class MenuPrincipal {

    //Atributos Menu Principal
    private static boolean estadoMenu = true;

    //Constructor privado
    private MenuPrincipal () {}

    //Métodos Menú Principal
    public static int mostrarMenuPrincipal () {
        System.out.println("Menú Principal \n" +
                "(1) Jugar partida \n" +
                "(2) Ranking \n" +
                "(3) Histórico \n" +
                "(4) Jugadores registrados \n" +
                "(5) Salir del juego");
        return insertarOpcion(1,5);
    }

    public static int insertarOpcion (int min, int max) {
        //min: Opcion mínima disponible
        //max: Opción máxima disponible

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int opcion = scanner.nextInt();
            if (opcion >= min && opcion <= max) {
                return opcion;
            } else {
                System.out.println("Inserte una opción válida");
            }
        }
    }

    public static boolean isEstadoMenu () {
        return estadoMenu;
    }

    public static void setEstadoMenu (boolean estado) {
        estadoMenu = estado;
    }

}
