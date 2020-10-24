package main.controller.tablero;

import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;

import main.exception.SudokuFileException;
import main.model.tablero.TableroModelController;
import main.model.tablero.TableroModelImpl;

import main.service.entry.Entry;

public class TableroControllerImpl implements TableroControllerView, TableroControllerModel, TableroController {
    private static TableroControllerImpl instance;
    private TableroModelController tableroModel;
    // private TableroView tableroView;

    private TableroControllerImpl() {
        tableroModel = TableroModelImpl.getInstance();
        // tableroView = TableroViewImpl.getInstance();
    }

    public static TableroControllerImpl getInstance() {
        if (instance == null) {
            instance = new TableroControllerImpl();
        }

        return instance;
    }

    @Override
    public void cargarTableroDesdeArchivo(File file) throws SudokuFileException {
        int[][] tableroNumerico = this.fileToMatrix(file);
        tableroNumerico = this.sudokuGenerator(tableroNumerico);

        tableroModel.cargarTablero(tableroNumerico);
    }

    @Override
    public void actualizarValor(int valor, int posX, int posY) {
        tableroModel.actualizarValorCelda(posX, posY, valor);
    }

    @Override
    public void actualizarSprite(int estado, int posX, int posY) {
        tableroModel.actualizarSpriteCelda(posX, posY, estado);
    }

    @Override
    public void verificarTablero() {
        tableroModel.verificarTablero();
    }

    @Override
    public void notificarInicioTablero(List<Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>>> celdas) {
        //tableroView.inicializarTablero(celdas);

    }

    @Override
    public void notificarCambios(List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas) {
        
        for (Entry<Entry<Integer, Integer>, ImageIcon> celdaActual : celdas) {
            Entry<Integer, Integer> coordenadasXY = celdaActual.getKey();
            int posX = coordenadasXY.getKey();
            int posY = coordenadasXY.getValue();
            ImageIcon spriteActual = celdaActual.getValue();

            //Pasarle las cosas juntadas a la view
        }

    }

    @Override
    public void notificarVerificacionTablero(boolean resultado) {
        //Deriva a la view el resultado

    }

    /**
     * Recibe un archivo el cual contiene la representacion del tablero y lo
     * transforma en una matriz de enteros.
     * 
     * @return Una matriz de entero que representa un tablero de sudoku completo.
     * @throws SudokuFileException Se lanza si el archivo no cumple la sintaxis o el
     *                             tablero interno es incorrecto.
     */
    protected int[][] fileToMatrix(File file) throws SudokuFileException {

        return null;
    }

    /**
     * Recibe una matriz que representa un tablero completo y la procesa en una
     * matriz incompleta en la cual es posible jugar.
     * 
     * @param tableroCompleto Matriz a procesar.
     * @return Una matriz que representa un tablero incompleto.
     */
    protected int[][] sudokuGenerator(int[][] tableroCompleto) {

        return null;
    }

}
