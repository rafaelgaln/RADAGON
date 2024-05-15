package juego.partidas;

import juego.core.MenuPrincipal;
import juego.utilidades.Constantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partida {

    //Atributos
    ArrayList<Jugador> listaJugadores;
    static Scanner scanner = new Scanner(System.in);

    //Constructor
    public Partida() {
        this.listaJugadores = new ArrayList<Jugador>();
    }

    //Métodos
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
            System.out.println("Error: Problema con el fichero " + Constantes.nombreFicheroUsuarios + ": " + e);
        }
        return false;
    }

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

    public boolean anyadirCpu () {
        int numCpusContados = 0;
        for (Jugador jugador : listaJugadores) {
            if (jugador instanceof Cpu) {
                numCpusContados++;
            }
        }
        String nombreCpu = "CPU" + numCpusContados+1;
        listaJugadores.add(new JugadorHumano(nombreCpu));
        System.out.println("CPU Añadida.");
        return true;
    }

    //Métodos - Fases de la partida
    public void bienvenida () {
        System.out.println("¡Empezando una nueva partida!");
    }

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
    }
}
