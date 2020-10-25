package main.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.view.botones.nuevo_juego.NuevoJuevoBtn;

@SuppressWarnings("serial")
public class GUI extends JFrame {
    private static GUI instance;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    private GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        this.setResizable(false);
        this.setTitle("Sudoku");

        JButton nuevoJuegoBtn = new NuevoJuevoBtn();


        contentPane.add(nuevoJuegoBtn);



	}
	
	public static GUI getInstance() {
		if( instance == null ) {
			instance = new GUI();
		}
		
		return instance;
	}
}
