package main.controller;

import java.util.List;

import main.exception.SudokuFileException;
import main.model.CeldaModel;
import main.model.TableroModel;
import main.model.TableroModelImpl;

public class TableroControllerImpl implements TableroController {
    private static TableroControllerImpl instance;
    private TableroModel tableroModel;
    // private TableroView tableroView;

    private TableroControllerImpl() {
        tableroModel = TableroModelImpl.getInstance();
        // tableroView = TableroViewImpl.getInstance();
    }

    public static TableroController getInstance() {
        if (instance == null) {
            instance = new TableroControllerImpl();
        }

        return instance;
    }

    @Override
    public void cargarTableroDesdeArchivo() throws SudokuFileException {
        // TODO Auto-generated method stub

    }

    @Override
    public int[][] fileToMatrix() throws SudokuFileException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[][] sudokuGenerator(int[][] tableroCompleto) {
        // TODO Auto-generated method stub
        return null;
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
    public void notificarCambios(List<CeldaModel> celdas) {

    }

    @Override
    public void verificarTablero() {
        // TODO Auto-generated method stub

    }
    

}
