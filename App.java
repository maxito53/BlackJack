package BlackJack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

/**
 * clase principal para ejecutar el juego de BlackJack con interfaz gráfica
 */
public class App extends JFrame {

    private static final long serialVersionUID = 1L;
    
    /**
     * panel princiapl del juego
     */
    private JPanel contentPane;
    /**
     * panel que muestra las cartas o la puntuación del jugador y dealer
     */
    private JPanel panelJuego;
    /**
     * botón para que el jugador saque una carta
     */
    private JButton sacarCartaBtn;
    /**
     * botón para que el jugador se plante
     */
    private JButton mePlantoBtn;
    /**
     * maneja la lógica del juego, es el juego en si
     */
    private Juego juego;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App frame = new App();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public App() {
    	setTitle("BlackJack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 610); // últimos width y heigth de la pestaña
        contentPane = new JPanel();
        contentPane.setBackground(new Color(69, 50, 46));
        contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        juego = new Juego();

        // panel de juego
        panelJuego = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                sacarCartas(g);
            }
        };
        panelJuego.setBackground(new Color(53, 101, 77));
        contentPane.add(panelJuego, BorderLayout.CENTER);

        // panel de botones y botones
        JPanel panelBtn = new JPanel();
        panelBtn.setBorder(new EmptyBorder(5, 0, 0, 0));
        panelBtn.setBackground(new Color(69, 50, 46));
        
        sacarCartaBtn = new JButton("Sacar carta");
        sacarCartaBtn.setForeground(new Color(255, 255, 255));
        sacarCartaBtn.setBackground(new Color(46, 139, 87));
        
        mePlantoBtn = new JButton("Me planto");
        mePlantoBtn.setForeground(new Color(255, 255, 255));
        mePlantoBtn.setBackground(new Color(165, 42, 42));
        
        panelBtn.add(sacarCartaBtn);
        panelBtn.add(mePlantoBtn);
        contentPane.add(panelBtn, BorderLayout.SOUTH);

        sacarCartaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juego.jugadorSacaCarta();
                if (juego.jugadorSePaso()) {
                    sacarCartaBtn.setEnabled(false);
                    mePlantoBtn.setEnabled(false);
                    resultadosJuego();
                }
                repaint();
            }
        });

        mePlantoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juego.dealerJuega();
                sacarCartaBtn.setEnabled(false);
                mePlantoBtn.setEnabled(false);
                repaint();
                resultadosJuego();
            }
        });
        
        // menú de arriba con sus opciones
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(69, 50, 46));
		
		JMenu menuUtil = new JMenu("Funciones útiles");
		menuUtil.setForeground(new Color(255, 255, 255));
		menuBar.add(menuUtil);
		
		JMenuItem resetearJuego = new JMenuItem("Volver a empezar");
        resetearJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juego = new Juego();
                sacarCartaBtn.setEnabled(true);
                mePlantoBtn.setEnabled(true);
                repaint();
            }
        });
        resetearJuego.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK)); // Ctrl + r para abrir
		
        menuUtil.add(resetearJuego);
        
        JMenuItem cerrarJuego = new JMenuItem("Cerrar juego");
        cerrarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cerrarJuego.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); // Ctrl + e para abrir
        
        menuUtil.add(cerrarJuego);
        
		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setForeground(new Color(255, 255, 255));
		menuBar.add(menuAyuda);

		JMenuItem comoSeJuega = new JMenuItem("Como se juega");
		comoSeJuega.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JOptionPane.showMessageDialog(null, 
                        "El objetivo del Blackjack es conseguir una mano con un valor lo más cercano posible a 21 sin pasarse.\n" +
                        "Cada carta tiene un valor: las cartas del 2 al 10 valen su número, las figuras (J, Q, K) valen 10, y los Ases valen 1 u 11.\n" +
                        "\n" +
                        "1. Comienzas con dos cartas, y el dealer también recibe cartas, aunque ocultas.\n" +
                        "2. Puedes elegir 'Sacar carta' para recibir otra carta o 'Me planto' para mantener tu mano actual.\n" +
                        "3. Si tu mano supera 21, pierdes automáticamente.\n" +
                        "4. Una vez que decides quedarte, el dealer revela su carta oculta y juega según reglas específicas: debe pedir cartas\n" +
                        "    hasta alcanzar al menos 17 puntos y quedarse si tiene 17 o más.\n" +
                        "5. Ganas si tu puntuación es mayor que la del dealer sin pasarte de 21, o si el dealer se pasa de 21.\n" +
                        "6. En caso de empate, pues empate, nadie gana.\n", 
                        "Como se juega al Blackjack", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		comoSeJuega.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // Ctrl + o para abrir
		
		JMenuItem acercaDe = new JMenuItem("Acerca de");
		acercaDe.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(null, "BlackJack\nHecho por: Max Tura Aguilera\nVersión 1.0", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		acercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK)); // Ctrl + i para abrir
		
		menuAyuda.add(comoSeJuega);
		menuAyuda.add(acercaDe);

		setJMenuBar(menuBar);
    }

    /**
     * muestra las cartas del jugador y el delear como sus puntuaciones en el panel
     * @param g el objeto Graphics utilizado para dibujar las cartas y puntuaciones
     */
    private void sacarCartas(Graphics g) {
    	
        Jugador jugador = juego.getJugador();
        Jugador dealer = juego.getDealer();

        // sacar las cartas del dealer
        for (int i=0 ; i<dealer.getMano().size() ; i++) {
        	
            Carta carta = dealer.getMano().get(i);
            String cartaImgRuta = carta.imgRuta();

            try {
                Image cartaImg = new ImageIcon(getClass().getResource(cartaImgRuta)).getImage();
                g.drawImage(cartaImg, 50 + i * 120, 50, 100, 140, null);
            } catch (Exception e) {
                System.err.println("Error al cargar la imagen: " + cartaImgRuta);
                g.drawString(carta.toString(), 50 + i * 120, 50); // si no se encuentra la imagen de la carta se muestra el toString de esta
            }
            
        }

        // sacar la carta oculta del dealer si es necesario
        if (!juego.isTurnoDealer()) {
            try {
                Image cartaOcultaImg = new ImageIcon(getClass().getResource("cards/BACK.png")).getImage();
                g.drawImage(cartaOcultaImg, 50, 50, 100, 140, null);
            } catch (Exception e) {
                System.err.println("Error al cargar la imagen de la carta oculta");
            }
        }

        // sacar las cartas del jugador
        for (int i=0 ; i<jugador.getMano().size() ; i++) {
        	
            Carta carta = jugador.getMano().get(i);
            String cartaImgRuta = carta.imgRuta();

            try {
                Image cartaImg = new ImageIcon(getClass().getResource(cartaImgRuta)).getImage();
                g.drawImage(cartaImg, 50 + i * 120, 300, 100, 140, null);
            } catch (Exception e) {
                System.err.println("Error al cargar la imagen: " + cartaImgRuta);
                g.drawString(carta.toString(), 50 + i * 120, 300);
            }
            
        }

        // puntuaciones
        g.setColor(Color.WHITE);
        g.drawString("Tu puntuación: " + jugador.getPuntuacion(), 50, 280);
        if (juego.isTurnoDealer()) {
            g.drawString("Puntuación de dealer: " + dealer.getPuntuacion(), 50, 220);
        } else {
            g.drawString("Puntuación de dealer: ?", 50, 220);
        }
        
    }

    /**
     * muestra el resultado del juego (ganador, perdedor o empate) mediante un mensaje en una ventana
     */
    private void resultadosJuego() {
    	
        String mensaje;
        
        if (juego.jugadorSePaso()) {
            mensaje = "Perdiste! Te pasaste de 21";
        } else if (juego.dealerSePaso()) {
            mensaje = "Ganaste! Dealer se pasó de 21";
        } else {
            int puntuacionJugador = juego.getJugador().getPuntuacion();
            int puntuacionDealer = juego.getDealer().getPuntuacion();

            if (puntuacionJugador > puntuacionDealer) {
                mensaje = "Ganaste!";
            } else if (puntuacionJugador < puntuacionDealer) {
                mensaje = "Perdiste!";
            } else {
                mensaje = "Empate!";
            }
        }
        JOptionPane.showMessageDialog(this, mensaje, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
        
    }
}
