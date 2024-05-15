package juego.partidas;

import juego.core.MenuPrincipal;

/**
 * @author Rafael Galán López
 * @since 1.0
 * @version 1.0
 * @see Jugador
 * @see JugadorHumano
 * Clase INSTANCIABLE hija de Jugador. Estas instancias se usan para representar las acciones de la CPU
 * y como se comportan a la hora de responder
 */
public class Cpu extends Jugador {

    //Constructor

    /**
     * Constructor
     * @param nombre
     * @since 1.0
     */
    public Cpu(String nombre) {
        setNombre(nombre);
        setPuntos(0);
    }

    //Métodos heredados

    /**
     * Método para contestar preguntas de Lengua.
     * Usa como parámetro la palabra correcta para que la CPU sepa la respuesta correcta.
     * 50% de Acertar o Fallar
     * Si falla responde con que no sabe la respuesta.
     * @param palabraCorrecta La respuesta correcta a la pregunta
     * @return Respuesta por parte de la CPU
     * @since 1.0
     */
    @Override
    public String contestarLengua(String palabraCorrecta) {
        int numeroAleatorio = (int) (Math.random()*2)+1;

        if (numeroAleatorio == 1) {
            System.out.println("- Respuesta: " + palabraCorrecta);
            return palabraCorrecta;
        } else {
            System.out.println("- Respuesta: " + "¡No se la respuesta!");
            return "¡No se la respuesta!";
        }
    }

    /**
     * Método para contestar preguntas de Mates.
     * Usa como parámetro la palabra correcta para que la CPU sepa la respuesta correcta.
     * 50% de Acertar o Fallar
     * Si falla responde la respuesta acertada más 1, siempre "fallando por poco"
     * @param respuestaCorrecta La respuesta correcta a la pregunta
     * @return Respuesta por parte de la CPU
     * @since 1.0
     */
    @Override
    public double contestarMates(double respuestaCorrecta) {
        int numeroAleatorio = (int) (Math.random()*2)+1;

        if (numeroAleatorio == 1) {
            System.out.println("- Respuesta: " + respuestaCorrecta);
            return respuestaCorrecta;
        } else {
            System.out.println("- Respuesta: " + (respuestaCorrecta+1));
            return (respuestaCorrecta+1);
        }
    }

    /**
     * Método para contestar preguntas de Inglés.
     * Usa como parámetro la palabra correcta para que la CPU sepa la respuesta correcta.
     * 50% de Acertar o Intentar
     * Si lo intenta, dirá una opción aleatorio, pudiendo acertar.
     * @param opcionCorrecta La respuesta correcta a la pregunta
     * @return Respuesta por parte de la CPU
     * @since 1.0
     */
    public int contestarIngles(int opcionCorrecta) {
        int numeroAleatorio = (int) (Math.random()*2)+1;

        if (numeroAleatorio == 1) {
            System.out.println("- Respuesta: " + opcionCorrecta);
            return opcionCorrecta;
        } else {
            int opcionAleatoria = (int) (Math.random()*3)+1;
            System.out.println("- Respuesta: " + opcionAleatoria);
            return opcionAleatoria;
        }
    }
}
