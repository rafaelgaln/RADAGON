package juego.partidas;

public abstract class Jugador {

    //Atributos heredados
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Métodos heredados
    public abstract String contestarLengua(String palabraCorrecta);
    public abstract double contestarMates(double respuestaCorrecta);
    public abstract int contestarIngles(int opcionCorrecta);
}