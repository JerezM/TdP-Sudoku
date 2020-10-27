package main.view.opciones_numeros;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.view.tablero.TableroViewOpciones;
import main.view.tablero.TableroViewImpl;

@SuppressWarnings("serial")
public class OpcionesNumeros extends JPanel {
    
    private TableroViewOpciones tablero;
    private JButton[] arrOpciones;

    public OpcionesNumeros() {
        this.tablero = TableroViewImpl.getInstance();

        this.setBounds(25, 358, 297, 33);
        this.setLayout( new GridLayout(1, 10) );

        this.inicializarArrayOpciones();

    }

    protected void notificarBtnPresionado(JButton btnPresionado) {
        boolean hayCeldaSeleccionada = tablero.hayCeldaSeleccionada();
        
        if(hayCeldaSeleccionada) {
            int valor = this.buscarValorBtnSeleccionado(btnPresionado);
            
            this.notificarBtnSeleccionado(valor);
        }

    }

    protected int buscarValorBtnSeleccionado(JButton btnPresionado) {
        int valor = 1;//eeveee

        return valor;
    }

    /**
     * Notifica al tableroView de que un boton fue seleccionado y le envia el valor obtenido del mismo.
     * @param valor Valor obtenido de presionar un boton.
     */
    protected void notificarBtnSeleccionado(int valor) {
        tablero.actualizarValor(valor);
    }

    /**
     * Inicializa el atributo arrOpciones y agrega el contenido de la misma a este panel.
     */
    protected void inicializarArrayOpciones(){
        arrOpciones = new JButton[10];

        //Agarro todas los ImageIcon desde los url
        ImageIcon imgBtn0 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-1/celda-1-estado-2.png"));

        //Inicializo todos los btns
        arrOpciones[0] = new JButton();
        arrOpciones[0].addActionListener(new OpcionesNumerosListener());
        arrOpciones[0].setSize(33, 33);

        //Modifico tamaño de los ImageIcon y se los seteo al btn
        Image image = imgBtn0.getImage();

		if (image != null) {  
			image = image.getScaledInstance(33, 33,  java.awt.Image.SCALE_SMOOTH);
            imgBtn0.setImage(image);
            arrOpciones[0].setIcon(imgBtn0);
		}
        
        //Agrego los btns al panel
        this.add(arrOpciones[0]);

    }
}
