package juego.partidas;

public abstract class Jugador {

    //Atributos heredados
    String nombre;

    //Métodos heredados
    public abstract String contestarLengua(String palabraCorrecta);
    public abstract double contestarMates(double respuestaCorrecta);
    public abstract int contestarIngles(int opcionCorrecta);
}