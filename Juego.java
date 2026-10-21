package BlackJack;

/**
 * clase que representa y gestiona el juego de BlackJack
 */
public class Juego {
	
	/**
	 * baraja de cartas utilizadas en el juego
	 */
    private final Baraja baraja;
    /**
     * el jugador que juega 
     */
    private final Jugador jugador;
    /**
     * el repartidor u oponente, llamarlo dealer queda mejor
     */
    private final Jugador dealer;
    /**
     * la carta inicial volteada del dealer
     */
    private Carta cartaVolteada;
    /**
     * indica si es el turno del dealer
     */
    private boolean turnoDealer;

    /**
     * constructor para crear un nuevo juego de BlackJack
     */
    public Juego() {
        baraja = new Baraja();
        jugador = new Jugador();
        dealer = new Jugador();
        turnoDealer = false; // false porque empieza el jugador
        empezarJuego();
    }

    /**
     * inicia el juego repartiendo 2 cartas al jugador y la volteada al dealer
     */
    private void empezarJuego() {
        jugador.recibeCarta(baraja.sacarCarta());
        jugador.recibeCarta(baraja.sacarCarta());

        cartaVolteada = baraja.sacarCarta();
        dealer.recibeCarta(baraja.sacarCarta());
    }

    /**
     * añade una carta a la mano del jugador
     */
    public void jugadorSacaCarta() {
        jugador.recibeCarta(baraja.sacarCarta());
        if (jugador.getPuntuacion() > 21) {
            turnoDealer = true;
        }
    }

    /**
     * simula la jugada del dealer, saca carta hasta que pasa de 17
     */
    public void dealerJuega() {
        turnoDealer = true; // Ahora es el turno del dealer
        dealer.recibeCarta(cartaVolteada);
        while (dealer.getPuntuacion() < 17) {
            dealer.recibeCarta(baraja.sacarCarta());
        }
    }
    
    /**
     * verifica si el jugador se pasó de 21
     * @return true si se pasó de 21, false si no
     */
    public boolean jugadorSePaso() {
        return jugador.getPuntuacion() > 21;
    }

    /**
     * verifica si el dealer se pasó de 21
     * @return true si se pasó de 21, false si no
     */
    public boolean dealerSePaso() {
        return dealer.getPuntuacion() > 21;
    }

    /**
     * devuelve al jugador principal
     * @return jugador principal
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * devuelve al dealer
     * @return dealer
     */
    public Jugador getDealer() {
        return dealer;
    }

    /**
     * devuelve la carta volteada del dealer
     * @return carta volteada del dealer
     */
    public Carta getCartaVolteada() {
        return cartaVolteada;
    }

    /**
     * devuelve si es el turno del dealer
     * @return true si es el turno del dealer, false si no
     */
    public boolean isTurnoDealer() {
        return turnoDealer;
    }
}
