package juego.utilidades;

import juego.partidas.Jugador;
import juego.partidas.JugadorHumano;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class GestionLogs {

    //Atributos
    public static String rutaFicheroLog = (Constantes.rutaDirectorioLogs + generarNombreFicheroLog());

    //Métodos
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

    public static String logJugadorAnyadido (String nombreJugador) {
        return ("(" + GestionFicheros.getDiaHoraActual() + ") Jugador añadido: " + nombreJugador);
    }

    public static String logJugadorBorrado (String nombreJugador) {
        return ("(" + GestionFicheros.getDiaHoraActual() + ") Jugador borrado: " + nombreJugador);
    }

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

    public static String logException (Exception e) {
        return "(" + GestionFicheros.getDiaHoraActual() + ") Ha ocurrido un error: " + e.getMessage();
    }

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
