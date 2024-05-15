package juego.partidas;

/**
 * @author Rafael Galán López
 * @since 1.0
 * @version 1.0
 * @see JugadorHumano
 * @see Cpu
 * Clase ABSTRACTA padre de JugadorHumano y Cpu. Recoge los métodos y atributos que serán heredados para
 * los tipos de jugador.
 */
public abstract class Jugador {

    //Atributos heredados
    /**
     * Nombre del jugador
     */
    private String nombre;

    /**
     * Puntos del jugador durante la partida
     */
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

    /**
     * Método que se hereda a JugadorHumano y Cpu que define como responden a una pregunta de Lengua
     * @param palabraCorrecta
     * @return Respuesta del jugador
     * @since 1.0
     */
    public abstract String contestarLengua(String palabraCorrecta);

    /**
     * Método que se hereda a JugadorHumano y Cpu que define como responden a una pregunta de Mates
     * @param respuestaCorrecta
     * @return Respesta del jugador
     * @since 1.0
     */
    public abstract double contestarMates(double respuestaCorrecta);

    /**
     * Método que se hereda a JugadorHumano y Cpu que define como responden a una pregunta de Inglés
     * @param opcionCorrecta
     * @return Respuesta del jugador
     * @since 1.0
     */
    public abstract int contestarIngles(int opcionCorrecta);
}