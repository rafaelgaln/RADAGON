package juego.core;

public abstract class MenuUsuario {

    public static void abrirMenuUsuario () {
        System.out.println("Menú Usuarios \n" +
                "(1) Ver usuarios \n" +
                "(2) Añadir usuario \n" +
                "(3) Borrar usuario \n" +
                "(4) Volver");

        int opcion = MenuPrincipal.insertarOpcion(1,4);

        switch (opcion) {
            case 1:
                //Ver usuarios
                break;
            case 2:
                //Añadir usuarios
                break;
            case 3:
                //Borrar usuarios
                break;
            case 4:
                //Volver al menú principal
                break;
        }

    }
}
