package juego.partidas;

import juego.core.MenuPrincipal;
import juego.utilidades.Constantes;
import juego.utilidades.GestionFicheros;
import juego.utilidades.GestionLogs;
import juego.utilidades.GestionPreguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rafael Galán López
 * @since 1.0
 * @version 1.0
 * @see JugadorHumano
 * @see Cpu
 * @see Jugador
 * Clase INSTANCIABLE para crear partidas y jugarlas.
 * Primero se elige la longitud de la partida
 * Segundo se eligen los jugadores (Humanos y CPU)
 * Tercero se juega
 * Último se hacen métodos Post-partida
 */
public class Partida {

    //Atributos
    /**
     * Lista de jugadores de la partida
     */
    private ArrayList<Jugador> listaJugadores;

    /**
     * List de ganadores de la partida
     */
    private ArrayList<Jugador> listaGanadores;

    /**
     * Número de rondas de la partida
     */
    private int numeroRondas;

    /**
     * Scanner de la clase para preparar la partida
     */
    static Scanner scanner = new Scanner(System.in);

    //Constructor

    /**
     * Constructor de la partida para instanciarla
     */
    public Partida() {
        this.listaJugadores = new ArrayList<Jugador>();
        this.listaGanadores = new ArrayList<Jugador>();
    }

    //Métodos

    /**
     * Método para buscar un usuario mediante el String proporcionado en el parámetro en el fichero
     * "usuarios.csv" Se usa para validar si el usuario está registrado para jugar la partida
     * @param nombreUsuario Nombre de usuario a buscar en "usuarios.csv"
     * @return true si el usuario está, false si no.
     * @since 1.0
     */
    public boolean buscarUsuario(String nombreUsuario) {

        try {
            List<String> lineas = Files.readAllLines(Paths.get(Constantes.rutaFicheroUsuarios));
            for (String linea : lineas) {
                String[] columnas = linea.split(",");
                if (columnas.length >= 1 && columnas[0].equals(nombreUsuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: Problema con el fichero " + Constantes.nombreFicheroUsuarios + ": " + e);
            System.out.println("No se pudo encontrar al jugador");
        }
        return false;
    }

    /**
     * Método que valida si el nombre de usuario proporcionado puede jugar una partida
     * Primero busca si está registrado, y luego si está ya añadido.
     * @param nombreUsuario Nombre del usuario insertado
     * @return true si se ha añadido, false si no
     * @since 1.0
     */
    public boolean anyadirUsuario (String nombreUsuario) {
        boolean nombreRegistrado = buscarUsuario(nombreUsuario);
        if (nombreRegistrado) {
            for (Jugador jugador : listaJugadores) {
                if (nombreUsuario.equals(jugador.getNombre())) {
                    System.out.println("No se pueden registrar el mismo usuario 2 veces. Escoja otro.");
                    return false;
                }
            }
            listaJugadores.add(new JugadorHumano(nombreUsuario));
            System.out.println("Jugador añadido.");
            return true;
        } else {
            System.out.println("Jugador no encontrado.");
            return false;
        }
    }

    /**
     * Método para añadir una CPU al ArrayList listaJugadores para la partida. La añade
     * automáticamente al seleccionar la opción
     * @return true si se añade, false si no
     * @since 1.0
     */
    public boolean anyadirCpu () {
        int numCpusContados = 0;
        for (Jugador jugador : listaJugadores) {
            if (jugador instanceof Cpu) {
                numCpusContados++;
            }
        }
        String nombreCpu = ("CPU" + (numCpusContados+1));
        listaJugadores.add(new Cpu(nombreCpu));
        System.out.println("CPU Añadida.");
        return true;
    }

    /**
     * Método que actualiza las estadísticas de los jugadores después de la aprtida en el fichero "usuarios.csv"
     * @since 1.0
     */
    public void actualizarStats() {

        try {
            List<String> lineas = Files.readAllLines(Paths.get(Constantes.rutaFicheroUsuarios));

            for (Jugador jugador : listaJugadores) {
                if (jugador instanceof JugadorHumano) {
                    for (int i = 0; i < lineas.size(); i++) {
                        String[] linea = lineas.get(i).split(",");
                        String nombre = linea[0].trim();
                        if (nombre.equals(jugador.getNombre())) {
                            int partidasJugadas = Integer.parseInt(linea[1].trim()) + 1;
                            linea[1] = String.valueOf(partidasJugadas);

                            if (listaGanadores.contains(jugador)) {
                                int partidasGanadas = Integer.parseInt(linea[2].trim()) + 1;
                                linea[2] = String.valueOf(partidasGanadas);
                            }

                            lineas.set(i, String.join(",", linea));
                            break;
                        }
                    }
                }
            }

            Files.write(Paths.get(Constantes.rutaFicheroUsuarios), lineas);
            System.out.println("Se han actualizado las estadísticas de los jugadores");
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.err.println("Error: Hubo un problema al actualizar las estadísticas: " + e.getMessage());
        }
    }

    /**
     * Método para limpiar los ArrayList después de la partida
     * @since 1.0
     */
    public void limpiarListas () {
        listaJugadores.clear();
        listaGanadores.clear();
    }

    /**
     * Método para añadir los datos de la partida al fichero "historico.txt"
     * @since 1.0
     */
    public void anyadirHistorico () {

        String nombrePartida = "Partida (" + GestionFicheros.getDiaHoraActual() + ")" + System.lineSeparator();
        String infoJugador = null;
        try {
            Files.writeString(Paths.get(Constantes.rutaFicheroHistorico), nombrePartida, StandardOpenOption.APPEND);
            for (Jugador jugador : listaJugadores) {
                if (listaGanadores.contains(jugador)) {
                    infoJugador = jugador.getNombre() + ": (Puntos: " + jugador.getPuntos() + ") GANADOR" + System.lineSeparator();
                } else {
                    infoJugador = jugador.getNombre() + ": (Puntos: " + jugador.getPuntos() + ")" + System.lineSeparator();
                }
                Files.writeString(Paths.get(Constantes.rutaFicheroHistorico), infoJugador, StandardOpenOption.APPEND);
            }
            Files.writeString(Paths.get(Constantes.rutaFicheroHistorico), System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: No se pudo actualizar el histórico.");
        }
    }

    //Métodos - Fases de la partida

    /**
     * Método que imprime un mensaje al empezar la partida
     * @since 1.0
     */
    public void bienvenida () {
        System.out.println("¡Empezando una nueva partida!");
    }

    /**
     * Método para determinar el tamaño de la partida, eligiendo entre 4 tipos de partidas:
     * Rápidas, cortas, normales, largas.
     * @since 1.0
     */
    public void tamanioPartida () {
        System.out.println("Elegid el tipo de partida a jugar \n" +
                "1) Partida rápida (3 rondas) \n" +
                "2) Partida corta (5 rondas) \n" +
                "3) Partida normal (10 rondas) \n" +
                "4) Partida larga (20 rondas)");
        int opcion = MenuPrincipal.insertarOpcion(1,4);
        switch (opcion) {
            case 1:
                numeroRondas = Constantes.RONDAS_PARTIDA_RAPIDA;
                System.out.println("¡Se jugará una partida rápida!");
                break;
            case 2:
                numeroRondas = Constantes.RONDAS_PARTIDAS_CORTA;
                System.out.println("¡Se jugará una partida corta!");
                break;
            case 3:
                numeroRondas = Constantes.RONDAS_PARTIDAS_NORMAL;
                System.out.println("¡Se jugará una partida normal!");
                break;
            case 4:
                numeroRondas = Constantes.RONDAS_PARTIDAS_LARGA;
                System.out.println("¡Se jugará una partida larga!");
                break;
        }
    }

    /**
     * Método para escoger los jugadores para lapartida y añadirlos al ArrayList listaJugadores
     * @since 1.0
     */
    public void escogerJugadores() {

        for (int i = 0; i<Constantes.MAX_JUGADORES; i++) {
            boolean jugadorValido = false;
            System.out.println("Se deben elegir los tipos de jugadores para la partida.");
            while (!jugadorValido) {
                System.out.println("Elige tipo de jugador " + (i+1) + "\n" +
                        "1) Usuario registrado \n" +
                        "2) CPU");
                int opcion = MenuPrincipal.insertarOpcion(1,2);
                switch (opcion) {
                    case 1: //Añadir jugador
                        System.out.print("Introduce el nombre del usuario registrado: ");
                        String nombreUsuario = scanner.nextLine();
                        jugadorValido = anyadirUsuario(nombreUsuario);
                        break;
                    case 2: //Añadir CPU
                        jugadorValido = anyadirCpu();
                        break;
                }
            }
        }
        System.out.println("Los jugadores de la partida:");
        for (Jugador jugador : listaJugadores) {
            System.out.println("- " + jugador.getNombre());
        }
        System.out.println("Introduce un valor para continuar: ");
        scanner.nextLine();
    }

    /**
     * Método en donde se juega la partida. Termina cuando se alcance el máximo de rondas jugadas.
     * @since 1.0
     */
    public void jugarPartida () {

        GestionLogs.escribirLog(GestionLogs.logNuevaPartida(listaJugadores));
        for (int i = 1; i<=numeroRondas; i++) {
            System.out.println("¡Empieza la ronda " + i + "!");
            for (Jugador jugador : listaJugadores) {
                System.out.println("(Ronda " + i + ") ¡Turno de " + jugador.getNombre() + "!");
                boolean preguntaAcertada = GestionPreguntas.preguntaAleatoria(jugador);
                if (preguntaAcertada) {
                    jugador.setPuntos(jugador.getPuntos()+1);
                }
                if (!(listaJugadores.indexOf(jugador) == 3)) {
                    System.out.println("Introduce un valor para continuar con el siguiente jugador: ");
                } else {
                    System.out.println("Introduce un valor para terminar la ronda:");
                }
                scanner.nextLine();

            }
            System.out.println("¡Ronda " + i + " finalizada! Puntos:");
            for (Jugador jugador : listaJugadores) {
                System.out.println("- " + jugador.getNombre() + ": " + jugador.getPuntos());
            }
            if (!(i == numeroRondas)) {
                System.out.println("Introduce un valor para continuar con la siguiente ronda: ");
            } else {
                System.out.println("Introduce un valor para terminar la partida: ");
            }
            scanner.nextLine();
        }
    }

    /**
     * Método donde se realizan sentencias post-partida: limpiar listas,
     * añadir al histórico, actualizar estadísticas...
     * También se calcula los ganadores, o ganador
     * @since 1.0
     */
    public void finPartida () {
        //Puntuación máxima obtenida
        int maxPuntosObtenidos = 0;
        for (Jugador jugador : listaJugadores) {
            if (jugador.getPuntos() > maxPuntosObtenidos) {
                maxPuntosObtenidos = jugador.getPuntos();
            }
        }
        //Obtener ganadores
        for (Jugador jugador : listaJugadores) {
            if (jugador.getPuntos() == maxPuntosObtenidos) {
                listaGanadores.add(jugador);
            }
        }

        //Imprimir ganadores
        if (listaGanadores.size() == 1) {
            Jugador ganador = listaGanadores.get(0);
            System.out.println("El ganador de la partida es " + ganador.getNombre());
        } else {
            System.out.println("Los ganadores son de la partida son...");
            for (Jugador jugador : listaGanadores) {
                System.out.println(" - " + jugador.getNombre());
            }
        }

        GestionLogs.escribirLog(GestionLogs.logFinPartida(listaJugadores, listaGanadores));
        actualizarStats();
        anyadirHistorico();
        limpiarListas();

    }

    //Getters y Setters
    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public int getNumeroRondas() {
        return numeroRondas;
    }

    public ArrayList<Jugador> getListaGanadores() {
        return listaGanadores;
    }

    public void setListaGanadores(ArrayList<Jugador> listaGanadores) {
        this.listaGanadores = listaGanadores;
    }

    public void setNumeroRondas(int numeroRondas) {
        this.numeroRondas = numeroRondas;
    }
}
