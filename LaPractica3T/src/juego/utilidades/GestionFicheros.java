package juego.utilidades;

import juego.partidas.Cpu;
import juego.partidas.Jugador;
import juego.partidas.JugadorHumano;
import juego.partidas.Partida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Rafael Galán López
 * @since 1.0
 * @version 1.0
 * Clase ABSTRACTA con métodos ESTÁTICOS para gestionar el control de ficheros
 */
public abstract class GestionFicheros {

    //Métodos - Control de Ficheros

    /**
     * Método para crear un fichero. Se proporciona la ruta, y el nombre del fichero.
     * @param rutaFichero Ruta del fichero Path
     * @param nombreFichero Nombre del fichero
     * @since 1.0
     */
    public static void crearFichero(String rutaFichero, String nombreFichero) {

        Path pathRutaFichero = Paths.get(rutaFichero);
        try {
            if (!Files.exists(pathRutaFichero)) {
                Files.createFile(pathRutaFichero);
                System.out.println("Aviso: Fichero '" + nombreFichero + "' creado.");
            }
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Aviso: No se ha podido crear el fichero '" + nombreFichero + "': " + e);
        }
    }

    /**
     * Método para comprobar los ficheros que no se pueden generar automáticamente
     * como es el caso de "ingles.txt" o "diccionario.txt"
     * Si están desaparecidos, da el aviso.
     * @param rutaFichero Ruta del fichero Path
     * @param nombreFichero Nombre del fichero
     * @since 1.0
     */
    public static void checkFichero(String rutaFichero, String nombreFichero) {
        Path pathRutaFichero = Paths.get(rutaFichero);
        if (!Files.exists(pathRutaFichero)) {
            GestionLogs.escribirLog("(" + getDiaHoraActual() + ") No se encontró el fichero " + nombreFichero + ", debes añadirlo manualmente");
            System.out.println("Aviso: No se encontró el fichero '" + nombreFichero + "'. \n" +
                    "- Este fichero no puede ser creado y debe ser insertado manualmente en el directorio 'ficheros'.");
        }
    }

    /**
     * Método para crear directorios
     * @param rutaDirectorio Ruta del directorio Path
     * @since 1.0
     */
    public static void crearDirectorio(String rutaDirectorio) {

        Path pathRutaDirectorio = Paths.get(rutaDirectorio);
        try {
            if (!Files.exists(pathRutaDirectorio)) {

                Files.createDirectory(pathRutaDirectorio);
                System.out.println("Aviso: Directorio '" + rutaDirectorio + "' creado.");
            }
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Aviso: No se ha podido crear el fichero '" + rutaDirectorio + "': " + e);
        }
    }

    //Métodos

    /**
     * Métodos para generar El día y hora en String. Utilizado para los logs y el histórico
     * @return El día y la hora en String
     * @since 1.0
     */
    public static String getDiaHoraActual() {

        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaString = fechaHoy.format(formatoFecha);

        LocalTime horaHoy = LocalTime.now();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        String horaString = horaHoy.format(formatoHora);

        return (fechaString + " - " + horaString);
    }

}
