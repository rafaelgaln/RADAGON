package juego.partidas;

public abstract class Jugador {

    //Atributos heredados
    private String nombre;
    private int puntos;

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    //Métodos heredados
    public abstract String contestarLengua(String palabraCorrecta);
    public abstract double contestarMates(double respuestaCorrecta);
    public abstract int contestarIngles(int opcionCorrecta);
}