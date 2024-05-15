package juego.partidas;

public class Cpu extends Jugador {

    //Constructor
    public Cpu(String nombre) {
        setNombre(nombre);
        setPuntos(0);
    }

    //Métodos heredados
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
