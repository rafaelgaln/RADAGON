package juego.core;
import juego.partidas.Partida;
import juego.utilidades.Constantes;
import juego.utilidades.GestionLogs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class MenuPrincipal {

    //Atributos Menu Principal
    private static boolean estadoMenu = true;
    static Scanner scanner = new Scanner(System.in);

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

    public static void mostrarRanking() {
        try {
            List<String> lineas = Files.lines(Path.of(Constantes.rutaFicheroUsuarios))
                    .sorted((linea1, linea2) -> {
                        int ganadas1 = Integer.parseInt(linea1.split(",")[2]);
                        int ganadas2 = Integer.parseInt(linea2.split(",")[2]);
                        return Integer.compare(ganadas2, ganadas1);
                    })
                    .toList();

            System.out.println("Ranking de jugadores:");
            for (int i = 0; i < lineas.size(); i++) {
                String[] datos = lineas.get(i).split(",");
                String nombre = datos[0];
                int ganadas = Integer.parseInt(datos[2]);
                System.out.println((i+1) + ". " + nombre + " - Partidas ganadas: " + ganadas);
            }

        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: No se ha podido leer el fichero: " + e.getMessage());
        } catch (NumberFormatException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: Datos erroneos en las partidas ganadas: " + e.getMessage());
        }
    }

    public static void mostrarHistorico() {
        try {
            List<String> lineasHistorico = Files.readAllLines(Paths.get(Constantes.rutaFicheroHistorico));
            System.out.println("Mostrando historico...");
            for (String linea : lineasHistorico) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: No se pudo mostrar el histórico de partidas: " + e);;
        }
        System.out.println("Aquí termina el histórico. \n" +
                "");
    }

    public static int insertarOpcion (int min, int max) {
        //min: Opcion mínima disponible
        //max: Opción máxima disponible

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                int opcion = scanner.nextInt();
                if (opcion >= min && opcion <= max) {
                    return opcion;
                } else {
                    System.out.println("Inserte una opción válida");
                }
            } catch (InputMismatchException e) {
                //Creo que no hace falta Exception log aquí
                System.out.println("Inserte una opción válida, en números enteros");
                scanner.next();
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
