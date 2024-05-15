package juego.core;

import juego.main.Main;
import juego.utilidades.Constantes;
import juego.utilidades.GestionLogs;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class MenuUsuario {

    public static void abrirMenuUsuario() {
        System.out.println("Menú Usuarios \n" +
                "(1) Ver usuarios \n" +
                "(2) Añadir usuario \n" +
                "(3) Borrar usuario \n" +
                "(4) Volver");

        int opcion = MenuPrincipal.insertarOpcion(1, 4);

        switch (opcion) {
            case 1: //Ver usuarios
                MenuUsuario.verUsuarios();
                break;
            case 2: //Añadir usuario
                MenuUsuario.anyadirUsuario();
                break;
            case 3: //Borrar usuarios
                MenuUsuario.borrarUsuario();
                break;
            case 4: //Volver
                break;
        }

    }

    public static void verUsuarios() {
        System.out.println("Mostrando usuarios...");
        try {
            List<String> lineas = Files.readAllLines(Path.of(Constantes.rutaFicheroUsuarios));

            for (String linea : lineas) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    String nombre = datos[0];
                    int jugadas = Integer.parseInt(datos[1]);
                    int ganadas = Integer.parseInt(datos[2]);

                    System.out.println("Nombre: " + nombre + " - Jugadas: " + jugadas + " - Ganadas: " + ganadas);
                } else {
                    System.out.println("Aviso: Este usuario le falta información: (" + linea + ").");
                }
            }
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: Ha ocurrido un problema al mostrar los usuarios: " + e);
        } catch (NumberFormatException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: No se pudo convertir un número: " + e);
        }
    }

    public static boolean anyadirUsuario() {
        Path pathFicheroUsuarios = Paths.get(Constantes.rutaFicheroUsuarios);
        Scanner scanner = new Scanner(System.in);

        System.out.print("- Inserta tu nombre de usuario para registrarte: ");
        String nombreUsuario = scanner.nextLine();

        if (nombreUsuario.isBlank()) {
            System.out.println("Error: El nombre de usuario no puede estar vacío.");
            return false;
        }

        if (nombreUsuario.contains(",")) {
            System.out.println("Error: El nombre de usuario no puede contener comas.");
            return false;
        }

        nombreUsuario += ",0,0\n"; // Mantenemos las segundas y terceras columnas con "0,0"

        try {
            Files.writeString(pathFicheroUsuarios, nombreUsuario, StandardOpenOption.APPEND);
            System.out.println("Se ha añadido el usuario '" + nombreUsuario.split(",")[0] + "'.");
            GestionLogs.escribirLog(GestionLogs.logJugadorAnyadido(nombreUsuario.split(",")[0]));
            return true;
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: No se ha podido añadir el usuario: " + e.getMessage());
            return false;
        }
    }

    public static void borrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("- Inserta el nombre del usuario que quieres borrar: ");
        String nombreUsuarioBorrar = scanner.nextLine();

        List<String> lineas = new ArrayList<>();
        List<String> lineasNuevas = new ArrayList<>();
        boolean usuarioEncontrado = false;

        try {
            lineas = Files.readAllLines(Path.of(Constantes.rutaFicheroUsuarios));

            for (String linea : lineas) {
                String[] datos = linea.split(",");
                if (datos.length >= 1 && datos[0].equals(nombreUsuarioBorrar)) {
                    usuarioEncontrado = true;
                } else {
                    lineasNuevas.add(linea);
                }
            }

            if (!usuarioEncontrado) {
                System.out.println("El usuario '" + nombreUsuarioBorrar + "' no existe.");
                return;
            }

            Files.write(Path.of(Constantes.rutaFicheroUsuarios), lineasNuevas);
            System.out.println("Se ha borrado el usuario '" + nombreUsuarioBorrar + "'.");
            GestionLogs.escribirLog(GestionLogs.logJugadorBorrado(nombreUsuarioBorrar));
        } catch (IOException e) {
            GestionLogs.escribirLog(GestionLogs.logException(e));
            System.out.println("Error: No se pudo borrar el usuario: " + e.getMessage());
        }
    }
}