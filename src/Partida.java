import java.util.Scanner;

/**
 * Esta clase es la encargada de leer las coordenadas, colocar las fichas y comprobrar si hay fin del juego
 * @author Juan Antonio Jerez
 * @version 0.3
 */

public class Partida {


    public static final int MINDIMENSION = 3;
    public static final int MINFILA = 1;
    private Jugador[] jugadores;             // NO NULO, <<R>>
    private int turno;                       // [1-2]
    private Tablero tablero;                 // NO NULO , <<R>>
    private boolean finJuego;
    private Jugador ganador;                 // <<R>>
    private int dimension;

    /**
     * Constructor de Partida
     * @param j1 es el jugador 1
     * @param j2 es el jugador 2
     * @param dimension es el tamaño del tablero
     */

    public Partida(Jugador j1, Jugador j2, int dimension) {

        setDimension(dimension);
        jugadores = new Jugador[]{j1, j2};
        tablero = new Tablero(this.dimension, this.dimension);
        turno = 1;
        finJuego = false;

    }

    public void setDimension(int dimension) {
        assert dimension >= MINDIMENSION :
                "Error: la dimension del tablero tiene que ser mayor o igual que " + MINDIMENSION;
        this.dimension = dimension;
    }

    public void jugar() {

        do {
            Coordenada c;
            boolean correcto;

            System.out.println(tablero);
            System.out.println("ES EL TURNO DE: "+jugadorActual());


            do {
                c = leerCoordenada();
                correcto = tablero.esCoordenada(c);

                if (!correcto)
                    System.out.printf("COORDENADA INCORRECTA: RANGO VALIDO (%d-%d) \n", MINFILA, dimension);
                else if(!tablero.estaVacia(c))
                    System.out.printf("LA CASILLA DE LA COORDENADA (%d-%d) YA TIENE UNA FICHA \n", c.getFila()+1, c.getColumna()+1);

            } while (!correcto || !tablero.estaVacia(c));

            tablero.colocar(jugadorActual().getFicha(), c);
            cambiarTurno();


            if (hayGanador(c)) {
                finJuego = true;
                ganador = jugadorActual();
                cambiarTurno();
                System.out.println(tablero);
                ganador = jugadorActual();
                System.out.println("BRAVO !! HAS GANADO "+ ganador);

            } else if (hayEmpate()){
                finJuego = true;
                System.out.println("OHH !! LA PARTIDA HA FINALIZADO EN EMPATE  ");
            }



        } while (!finJuego);
    }

    /**
     * Metodo que comprueba si hay un ganador
     * @param c es la coordenada donde se comprueba si hay diagonal derecha, diagonal izquierda, linea horizontal y
     *          linea vertical
     * @return {@code true} si hay ganador
     */

    private boolean hayGanador(Coordenada c) {
        assert c != null : "Error la coordenada no puede ser nulo";

        if (tablero.hayDiagonalDerecha() || tablero.hayDiagonalIzquierda() || tablero.hayLineaHorizontal(c.getFila())
            || tablero.hayLineaVertical(c.getColumna()))
            return true;
        return false;
    }

    private boolean hayEmpate() {

        return tablero.hayTablas();
    }

    private Jugador jugadorActual() {
        return turno == 1 ? jugadores[turno-1] : jugadores[turno-1];
    }

    private void setTurno(int turno) {
        assert turno == 1 || turno == 2 : String.format("Error: el turno (%d) debe ser 1 o 2", turno);
        this.turno = turno;
    }

    private void cambiarTurno() {
        setTurno(turno == 1 ? 2 : 1);
    }

     private Coordenada leerCoordenada() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca la fila: ");
        int fila = sc.nextInt();
        System.out.print("Introduzca la columna: ");
        int columna = sc.nextInt();

        return new Coordenada(fila - 1, columna - 1);

    }


}
