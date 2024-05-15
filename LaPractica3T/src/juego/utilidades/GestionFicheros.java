package juego.utilidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionFicheros {

    public static void crearFichero (String rutaFichero, String nombreFichero) {

        Path pathRutaFichero = Paths.get(rutaFichero);
        try {
            if (!Files.exists(pathRutaFichero)) {
                Files.createFile(pathRutaFichero);
                System.out.println("Aviso: Fichero '" + nombreFichero +  "' creado.");
            }
        } catch (IOException e) {
            System.out.println("Aviso: No se ha podido crear el fichero '" + nombreFichero + "': " + e);
        }
    }

    public static void checkFichero (String rutaFichero, String nombreFichero) {
        Path pathRutaFichero = Paths.get(rutaFichero);
        if (!Files.exists(pathRutaFichero)) {
            System.out.println("Aviso: No se encontró el fichero '" + nombreFichero + "'. \n" +
                    "- Este fichero no puede ser creado y debe ser insertado manualmente en el directorio 'ficheros'.");
        }
    }

    public static void crearDirectorio(String rutaDirectorio) {

        Path pathRutaDirectorio = Paths.get(rutaDirectorio);
        try {
            if (!Files.exists(pathRutaDirectorio)) {

                Files.createDirectory(pathRutaDirectorio);
                System.out.println("Aviso: Directorio '" + rutaDirectorio + "' creado.");
            }
        } catch (IOException e) {
            System.out.println("Aviso: No se ha podido crear el fichero '" + rutaDirectorio + "': " + e);
        }
    }
}
