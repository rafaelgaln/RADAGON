package juego.utilidades;

import java.util.Scanner;

public final class Constantes {


    public Scanner scanner = new Scanner(System.in);

    //Ficheros y Directorios
    public static final String nombreDirectorioFicheros = "ficheros";
    public static final String nombreFicheroDiccionario = "diccionario.txt";
    public static final String nombreFicheroIngles = "ingles.txt";
    public static final String nombreFicheroUsuarios = "usuarios.csv";
    public static final String nombreFicheroHistorico = "historico.txt";

    public static final String rutaDirectorioFicheros = "ficheros/";
    public static final String rutaDirectorioLogs = "logs/";
    public static final String rutaFicheroUsuarios = rutaDirectorioFicheros + nombreFicheroUsuarios;
    public static final String rutaFicheroHistorico = rutaDirectorioFicheros + nombreFicheroHistorico;
    public static final String rutaFicheroDiccionario = rutaDirectorioFicheros + nombreFicheroDiccionario;
    public static final String rutaFicheroIngles = rutaDirectorioFicheros + nombreFicheroIngles;

    //Valores
    public static final int MAX_JUGADORES = 4;
    public static final int RONDAS_PARTIDA_RAPIDA = 3;
    public static final int RONDAS_PARTIDAS_CORTA = 5;
    public static final int RONDAS_PARTIDAS_NORMAL = 10;
    public static final int RONDAS_PARTIDAS_LARGA = 20;


}
