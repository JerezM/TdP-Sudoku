package main.view.opciones_numeros;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.view.tablero.TableroViewOpciones;
import main.view.opciones_numeros.factory.OpcionesBtnFactory;
import main.view.opciones_numeros.factory.OpcionesBtnFactoryImp;
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
        OpcionesBtnFactory btnFactory = new OpcionesBtnFactoryImp();

        //Agarro todas los ImageIcon desde los url
        ImageIcon imgBtn0 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-0/celda-0-estado-2.png"));
        ImageIcon imgBtn1 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-1/celda-1-estado-2.png"));
        ImageIcon imgBtn2 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-2/celda-2-estado-2.png"));
        ImageIcon imgBtn3 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-3/celda-3-estado-2.png"));
        ImageIcon imgBtn4 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-4/celda-4-estado-2.png"));
        ImageIcon imgBtn5 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-5/celda-5-estado-2.png"));
        ImageIcon imgBtn6 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-6/celda-6-estado-2.png"));
        ImageIcon imgBtn7 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-7/celda-7-estado-2.png"));
        ImageIcon imgBtn8 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-8/celda-8-estado-2.png"));
        ImageIcon imgBtn9 = new ImageIcon(this.getClass().getResource("/resources/sprites/celda-9/celda-9-estado-2.png"));


        //Inicializo todos los btns
        arrOpciones[0] = btnFactory.createOptionBtn(33, 33, imgBtn0);
        arrOpciones[1] = btnFactory.createOptionBtn(33, 33, imgBtn1);
        arrOpciones[2] = btnFactory.createOptionBtn(33, 33, imgBtn2);
        arrOpciones[3] = btnFactory.createOptionBtn(33, 33, imgBtn3);
        arrOpciones[4] = btnFactory.createOptionBtn(33, 33, imgBtn4);
        arrOpciones[5] = btnFactory.createOptionBtn(33, 33, imgBtn5);
        arrOpciones[6] = btnFactory.createOptionBtn(33, 33, imgBtn6);
        arrOpciones[7] = btnFactory.createOptionBtn(33, 33, imgBtn7);
        arrOpciones[8] = btnFactory.createOptionBtn(33, 33, imgBtn8);
        arrOpciones[9] = btnFactory.createOptionBtn(33, 33, imgBtn9);
        
        //Agrego los btns al panel
        for (int i = 0; i < arrOpciones.length; i++) {
            this.add(arrOpciones[i]);
        }
        

    }
}
