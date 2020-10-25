package main.view;

import java.awt.BorderLayout;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(1, 1));
        setContentPane(contentPane);
        this.setResizable(false);
        this.setTitle("Sudoku");

        JButton nuevoJuegoBtn = new NuevoJuevoBtn();
        JPanel tablero = TableroViewImpl.getInstance();



        contentPane.add(nuevoJuegoBtn);
        contentPane.add(tablero);



	}
	
	public static GUI getInstance() {
		if( instance == null ) {
			instance = new GUI();
		}
		
		return instance;
	}
}
