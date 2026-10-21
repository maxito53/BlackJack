package BlackJack;

import java.util.ArrayList;
import java.util.List;

/**
 * clase que representa a un jugador en BlackJack
 */
public class Jugador {
	
	/**
	 * lista de cartas que hacen la mano del jugador
	 */
    private final List<Carta> mano;
    /**
     * puntuación total del jugador, basado en el valor de las cartas de su mano
     */
    private int puntuacion;
    /**
     * contador de cuantos ases tiene el jugador en su mano
     */
    private int contadorAses;

    /**
     * constructor para crear un nuevo jugador con su mano, puntuación y número de ases a 0
     */
    public Jugador() {
        mano = new ArrayList<>();
        puntuacion = 0;
        contadorAses = 0;
    }

    /**
     * añade una carta nueva a la mano del jugador, comprobando si es un as
     * @param carta carta nueva que se recibe
     */
    public void recibeCarta(Carta carta) {
        mano.add(carta);
        puntuacion += carta.valorNumerico();
        if ("A".equals(carta.getValor())) {
            contadorAses++;
        }
        ajusteDeAses();
    }
    
    /**
     * ajusta la puntuación del jugador en caso de que haya ases en su mano y la puntuación sea mayor a 21
     * cada as se ajusta a 1 punto en lugar de 11 para evitar que el jugador se pase de 21
     */
    private void ajusteDeAses() {
        while (puntuacion > 21 && contadorAses > 0) {
            puntuacion -= 10;
            contadorAses--;
        }
    }

    /**
     * devuelve la puntuación del jugador
     * @return puntuación del jugador
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * devuelve la mano de cartas del jugador
     * @return mano de cartas del jugador
     */
    public List<Carta> getMano() {
        return mano;
    }
    
}