package main.view.tablero;

import java.util.List;

import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.controller.tablero.TableroControllerView;
import main.service.EstadosPosiblesCeldas;
import main.service.entry.Entry;
import main.view.GUI;
import main.view.celda.CeldaViewImpl;
import main.view.celda.CeldaViewTablero;

@SuppressWarnings("serial")
public class TableroViewImpl extends JPanel implements TableroViewCelda, TableroViewController, TableroViewOpciones, TableroViewVerificar {
    private static TableroViewImpl instance;

    private TableroControllerView controller;
    private GUI gui;

    private CeldaViewTablero[][] tableroCeldas;
    private CeldaViewTablero celdaSeleccionada;

    private TableroViewImpl() {
        tableroCeldas = new CeldaViewTablero[9][9];
        celdaSeleccionada = null;

        this.setBounds(74, 50, 441, 441);
        this.setLayout( new GridLayout(tableroCeldas.length, tableroCeldas[0].length) );
        this.setBackground(new Color(0,0,0));
        
    }

    public static TableroViewImpl getInstance() {
        if (instance == null) {
            instance = new TableroViewImpl();
        }

        return instance;
    }

    @Override
    public void inicializarTablero(List<Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>>> celdas) {
        
        for( Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>> celdaActual : celdas ) {
            boolean esInicial = celdaActual.getKey();

            Entry<Entry<Integer, Integer>, ImageIcon> datosCelda = celdaActual.getValue();
            Entry<Integer, Integer> coordenadasXY = datosCelda.getKey();
            int posX = coordenadasXY.getKey();
            int posY = coordenadasXY.getValue();
            ImageIcon sprite = datosCelda.getValue();

            tableroCeldas[posY][posX] = new CeldaViewImpl(esInicial, posX, posY, sprite);
            this.add((JLabel) tableroCeldas[posY][posX]);
        }

        this.gui = GUI.getInstance();
        this.gui.getContentPane().add(this);
        this.gui.validate();
    }

    @Override
    public void notificarCambios(List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas) {
        
        for( Entry<Entry<Integer, Integer>, ImageIcon> celdaActual : celdas ) {

            Entry<Integer, Integer> coordenadasXY = celdaActual.getKey();
            int posX = coordenadasXY.getKey();
            int posY = coordenadasXY.getValue();
            ImageIcon sprite = celdaActual.getValue();

            tableroCeldas[posY][posX].notificarCambio(sprite);
        }
        
        this.repaint();
    }

    @Override
    public void notificarVerificacionTablero(boolean resultado, List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas) {
        this.notificarCambios(celdas);

        if(resultado) {//Si el tablero esta completo correctamente
            JOptionPane.showInternalMessageDialog(null, "Has Ganado!!");
            this.gui.dispatchEvent( new WindowEvent(gui, WindowEvent.WINDOW_CLOSING) );
        }
    }

    @Override
    public void actualizarValor(int valor) {
        int posX = celdaSeleccionada.getPosX();
        int posY = celdaSeleccionada.getPosY();

        celdaSeleccionada = null;

        controller.actualizarValor(valor, posX, posY);

    }

    @Override
    public void actualizarSprite(int estado, int posX, int posY) {

        if (estado == EstadosPosiblesCeldas.CELDA_SELECCIONADA.getEstado()) {

            if (celdaSeleccionada == null) {
                celdaSeleccionada = tableroCeldas[posY][posX];
            }
            else {

                int celdaSeleccionadaPosX = celdaSeleccionada.getPosX();
                int celdaSeleccionadaPosY = celdaSeleccionada.getPosY();
                tableroCeldas[celdaSeleccionadaPosY][celdaSeleccionadaPosX].deseleccionar();

                celdaSeleccionada = tableroCeldas[posY][posX];
            }

        }
        else if (estado == EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado()) {
            celdaSeleccionada = null;
        }

        this.repaint();
        controller.actualizarSprite(estado, posX, posY);
    }

	@Override
	public void setController(TableroControllerView controller) {
		this.controller = controller;
	}

    @Override
    public boolean hayCeldaSeleccionada() {
        boolean haySeleccionada = (celdaSeleccionada != null);

        return haySeleccionada;
    }

    @Override
    public void verificarTablero() {
        if(tableroCeldas[0][0] != null) {//Si el tablero esta inicializado
            this.controller.verificarTablero();
        }
    }
    

    
}
