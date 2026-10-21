package BlackJack;

/**
 * clase que representa una carta de un mazo estándar de 52 naipes para jugar BlackJack
 */
public class Carta {
	
	/**
	 * valor númerico (o letra) de la carta, final porque no se puede cambiar
	 */
    private final String valor;
    /**
     * naipe de la carta (corazones, tréboles, etc), final porque no se puede cambiar
     */
    private final String naipe;

    /**
     * constructor para crear una carta
     * @param valor valor númerico (o letra) de la carta
     * @param naipe naipe de la carta (corazones, tréboles, etc)
     */
    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    /**
     * devuelve el valor númerico de la carta si el valor de esta es una letra
     * J, Q o K valen 10, A vale 11
     * @return valor de la carta si o si númerico
     */
    public int valorNumerico() {
        if ("JQK".contains(valor)) {
            return 10;
        }
        if ("A".equals(valor)) {
            return 11;
        }
        return Integer.parseInt(valor);
    }

    /**
     * genera la ruta a la imagen que representa la carta
     * @return ruta de la imagen
     */
    public String imgRuta() {
    	return String.format("cards/%s-%s.png", valor, naipe);
    }
    
    /**
     * devuelve el valor númerico de la carta
     * @return valor númerico de la carta
     */
    public String getValor() {
        return valor;
    }

    /**
     * devuelve el naipe de la carta
     * @return naipe de la carta
     */
    public String getNaipe() {
        return naipe;
    }

    /**
     * muestra en string la carta diciendo su valor y naipe
     * @return string representativo de la carta
     */
    @Override
    public String toString() {
        return String.format("%s de %s", valor, naipe);
    }
}