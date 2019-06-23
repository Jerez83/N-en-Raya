public class Principal {

    /**
     * @author Jerez83
     *
     */
    
    private static String jugador1;
    private static String jugador2;
    private static int dimension;

    public static void main(String[] args) {
        if (args.length == 3) {
            jugador1 = args[0];
            jugador2 = args[1];
            dimension = Integer.parseInt(args[2]);
        } else {
            System.out.println("Uso: alias1 alias2 dimensionTablero") ;
            System.exit(0);
        }

        Partida  p = new Partida(crearJugadores()[0], crearJugadores()[1], dimension);
        p.jugar();

    }

    private static Jugador[] crearJugadores() {
        Jugador[] jugadores = new Jugador[2];
        jugadores[0] = new Jugador(jugador1,new Ficha(TipoFicha.O));
        jugadores[1] = new Jugador(jugador2,new Ficha(TipoFicha.X));
        return jugadores;
    }

}
