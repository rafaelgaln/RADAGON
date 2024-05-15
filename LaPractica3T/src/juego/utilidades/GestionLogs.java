package juego.utilidades;

import juego.core.MenuPrincipal;
import juego.partidas.Jugador;
import juego.partidas.JugadorHumano;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Rafael Galán López
 * @since 1.0
 * @version 1.0
 * Clase ABSTRACTA con métodos ESTÁTICOS, que se encargan de generar los logs correctamente.
 * Opera hacia los ficheros "salida.log[AÑO,MES,DIA].
 */
public abstract class GestionLogs {

    //Atributos
    /**
     * Ruta del fichero de los logs. El día determina el nombre del fichero.
     */
    public static String rutaFicheroLog = (Constantes.rutaDirectorioLogs + generarNombreFicheroLog());

    //Métodos

    /**
     * Método utilizado para escribir los logs en los ficheros logs correctamente
     * @param log Mensaje que se va a escribir en el fichero. Contiene la fecha y hora
     */
    public static void escribirLog(String log) {
        try {
            String nombreFicheroLog = generarNombreFicheroLog();
            if (!Files.exists(Paths.get(rutaFicheroLog))) {
                Files.createFile(Paths.get(rutaFicheroLog));
            }
            log = log + System.lineSeparator();
            Files.writeString(Paths.get(rutaFicheroLog), log, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Aviso: No se pudo escribir un log: " + e);
        }
    }

    /**
     * Método que devuelve el mensaje de log cuando se añade un jugador
     * @param nombreJugador Nombre del jugador añadido
     * @return El mensaje log
     * @since 1.0
     */
    public static String logJugadorAnyadido (String nombreJugador) {
        return ("(" + GestionFicheros.getDiaHoraActual() + ") Jugador añadido: " + nombreJugador);
    }

    /**
     * Método que devuelve el mensaje de log cuando se borra un jugador
     * @param nombreJugador Nombre del jugador borrado
     * @return
     */
    public static String logJugadorBorrado (String nombreJugador) {
        return ("(" + GestionFicheros.getDiaHoraActual() + ") Jugador borrado: " + nombreJugador);
    }

    /**
     * Método que devuelve el mensaje de log cuando se empieza una partida
     * @param listaJugadores Lista de jugadores de la partida
     * @return El mensaje log
     * @since 1.0
     */
    public static String logNuevaPartida (ArrayList<Jugador> listaJugadores) {
        int contHumanos = 0;
        int contCpus = 0;

        for (Jugador jugador : listaJugadores) {
            if (jugador instanceof JugadorHumano) {
                contHumanos++;
            } else {
                contCpus++;
            }
        }

        return "(" + GestionFicheros.getDiaHoraActual() + ")" +
                " Inicio de partida con " + contHumanos + " jugadores humanos, " + contCpus + " jugadores de CPU";
    }

    /**
     * Método que devuelve el mensaje de log cuando se termina una partida
     * @param listaJugadores Lista de jugadores de la partida
     * @param listaGanadores Lista de ganadores de la partida
     * @return El mensaje log
     * @since 1.0
     */
    public static String logFinPartida (ArrayList<Jugador> listaJugadores, ArrayList<Jugador> listaGanadores) {

        if (listaGanadores.size() == 1) {
            return "(" + GestionFicheros.getDiaHoraActual() + ")" + " Fin de partida. Ha ganado " + listaGanadores.get(0).getNombre();
        } else {
            String ganadores = "";
            for (Jugador jugador : listaGanadores) {
                if (listaGanadores.get(listaGanadores.size()-1).equals(jugador)) {
                    ganadores += jugador.getNombre();
                } else {
                    ganadores += jugador.getNombre() + ", ";
                }
            }
            return "(" + GestionFicheros.getDiaHoraActual() + ")" + " Fin de partida. Han ganado " + ganadores;
        }
    }

    /**
     * Método que deuvleve el mensaje de log cuando ocurre una excepción
     * @param e La excepción
     * @return El mensaje de la excepción para el log
     * @since 1.0
     */
    public static String logException (Exception e) {
        return "(" + GestionFicheros.getDiaHoraActual() + ") Ha ocurrido un error: " + e.getMessage();
    }

    /**
     * Método que devuelve un String con el nombre del fichero log acorde al día
     * @return String con el nombre del fichero
     * @since 1.0
     */
    public static String generarNombreFicheroLog() {
        LocalDate fechaHoy = LocalDate.now();
        int dia = fechaHoy.getDayOfMonth();
        int mes = fechaHoy.getMonthValue();
        int anio = fechaHoy.getYear();

        if (mes < 10) {
            return ("salida.log." + anio + "0" + mes + dia);
        } else {
            return ("salida.log." + anio + mes + dia);
        }


    }


}
