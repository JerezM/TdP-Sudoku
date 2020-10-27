package main.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.view.botones.nuevo_juego.NuevoJuegoBtn;
import main.view.botones.verificar_tablero.VerificarTableroBtn;
import main.view.opciones_numeros.OpcionesNumeros;
import main.view.tablero.TableroViewImpl;

@SuppressWarnings("serial")
public class GUI extends JFrame {
    private static GUI instance;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    private GUI() {

        this.initComponents();

	}
	
	public static GUI getInstance() {
		if( instance == null ) {
			instance = new GUI();
		}
		
		return instance;
    }
    
    private void initComponents() {

        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 25, 755, 637);
        setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(233,233,233));
		
        JPanel opciones = new OpcionesNumeros();
		contentPane.add(opciones);
        
        JPanel tablero = TableroViewImpl.getInstance();
        contentPane.add(tablero);
		
		JButton btnNuevoJuego = new NuevoJuegoBtn();
		contentPane.add(btnNuevoJuego);
		
		JButton btnVerificarTablero = new VerificarTableroBtn();
		contentPane.add(btnVerificarTablero);
		
		JPanel timer = new JPanel();
		timer.setBounds(545, 128, 80, 17);
		contentPane.add(timer);
        timer.setLayout(null);
        timer.setBackground(new Color(233,233,0));

    }
}
