package main.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.view.botones.nuevo_juego.NuevoJuevoBtn;
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

        this.setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 528, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(233,0,0));
		
        JPanel tablero = TableroViewImpl.getInstance();
        contentPane.add(tablero);
		
		JPanel opciones = new JPanel();
		opciones.setBounds(25, 358, 297, 33);
		contentPane.add(opciones);
        opciones.setLayout(null);
        opciones.setBackground(new Color(0,0,233));
		
		JButton btnNuevoJuego = new NuevoJuevoBtn();
		contentPane.add(btnNuevoJuego);
		
		JButton btnVerificarTablero = new JButton("Verificar Tablero");
		btnVerificarTablero.setBounds(345, 64, 160, 23);
		contentPane.add(btnVerificarTablero);
		
		JPanel timer = new JPanel();
		timer.setBounds(345, 108, 111, 33);
		contentPane.add(timer);
        timer.setLayout(null);
        timer.setBackground(new Color(233,233,0));

    }
}
