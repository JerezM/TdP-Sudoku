package main.view.tablero;

import java.util.List;

import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.controller.tablero.TableroControllerImpl;
import main.controller.tablero.TableroControllerView;
import main.service.entry.Entry;
import main.view.GUI;
import main.view.celda.CeldaViewImpl;
import main.view.celda.CeldaViewTablero;

@SuppressWarnings("serial")
public class TableroViewImpl extends JPanel implements TableroViewCelda, TableroViewController, TableroView {
    private static TableroViewImpl instance;

    private TableroControllerView controller;

    private CeldaViewTablero[][] tableroCeldas;

    private TableroViewImpl() {

        controller = TableroControllerImpl.getInstance();

        tableroCeldas = new CeldaViewTablero[9][9];
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
        }

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

    }

    @Override
    public void notificarVerificacionTablero(boolean resultado, List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas) {
        this.notificarCambios(celdas);

        if(resultado) {//Si el tablero esta completo correctamente
            GUI gui = GUI.getInstance();

            JOptionPane.showInternalMessageDialog(null, "Has Ganado!!");
            gui.dispatchEvent( new WindowEvent(gui, WindowEvent.WINDOW_CLOSING) );
        }
    }

    @Override
    public void actualizarValor(int valor, int posX, int posY) {
        controller.actualizarValor(valor, posX, posY);
    }

    @Override
    public void actualizarSprite(int estado, int posX, int posY) {
        controller.actualizarSprite(estado, posX, posY);
    }
    
}
