package main.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


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
        
        JLabel celda = new CeldaView();
        contentPane.add(celda);

        /*
        celda.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                reDimensionar(label, grafico);
                label.setIcon(grafico);
            }
        });
        
        celda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                juego.accionar(c);
                reDimensionar(label,grafico);
            }
        });
        */


	}
	
	public static GUI getInstance() {
		if( instance == null ) {
			instance = new GUI();
		}
		
		return instance;
	}
}
