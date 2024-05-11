package juego.utilidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionFicheros {

    public static void crearFichero (String rutaFichero, String nombreFichero) {

        //Fichero usuarios
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
}
