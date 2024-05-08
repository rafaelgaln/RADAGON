package juego.core;
import java.util.Scanner;

/**
 * Clase
 * @author Rafael
 * @version 1.0
 * @since 08/05/2024
 */
public abstract class MenuPrincipal {

    /**
     * Constructor privado para que no se pueda instanciar esta clase
     */
    private MenuPrincipal () {}

    //Atributos Menu Principal
    private static boolean estadoPrograma = true;

    /**
     * Método que devuelve el estado del programa.
     * @return estadoPrograma
     */
    public static boolean getEstadoPrograma () {
        return estadoPrograma;
    }

    /**
     * Método utilizado para apagar el bucle que administra las fases del programa
     * y la mayoría del código de este
     */
    public static void apagarPrograma () {
        if (estadoPrograma == true) {
            estadoPrograma = false;
        }
    }

    /**
     * Método que imprime las opciones del menú principal, y pide al usuario la opción a escoger
     * @return El integer insertado por el usuario para elegir una opción del menú principal
     * @see @insertarOpcion()
     */
    public static int mostrarMenuPrincipal () {
        System.out.println("Menú Principal \n" +
                "(1) Jugar partida \n" +
                "(2) Ranking \n" +
                "(3) Histórico \n" +
                "(4) Jugadores registrados \n" +
                "(5) Salir del juego");
        return insertarOpcion(1,5);
    }

    /**
     * Método que lee el integer insertado por el usuario entrelos valores asginados
     * @param min (La opción mínima a escoger)
     * @param max (La opción máxima a escoger)
     * @return La opción del usuario entre los valores insertados
     */
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
}
