package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * clase que representa una baraja estándar de 52 cartas utilizada en el juego de BlackJack
 */
public class Baraja {
	
	/**
	 * lista de cartas que compone la baraja, final porque no se puede modificar
	 */
    private final List<Carta> cartas;

    /**
     * constructor para crear una baraja de cartas
     */
    public Baraja() {
    	
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        // nombro así a los naipes porque los usaré para la ruta de la imagen y las imágenes que me descargue ya los nombran
        // así y me da pereza cambiar los nombres, serían H --> Corazones (Hearts), D --> Diamantes (Diamonds), C --> Tréboles (Clubs)
        // y S --> Espadas (Spades)
        String[] naipes = {"H", "D", "C", "S"};
        cartas = new ArrayList<>();

        for (String naipe : naipes) {
            for (String valor : valores) {
                cartas.add(new Carta(valor, naipe));
            }
        }
        
        barajar();
        
    }

    /**
     * mezcla aleatoriamente la baraja
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }

    /**
     * saca una carta de la baraja
     * @return la carta sacada de la baraja
     * @throws IllegalStateException si la baraja está vacía se lanza la excepción
     */
    public Carta sacarCarta() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("La baraja está vacía");
        }
        return cartas.remove(cartas.size() - 1);
    }
    
}