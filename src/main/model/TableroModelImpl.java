package main.model;

import java.util.LinkedList;
import java.util.List;

import main.controller.TableroController;
import main.model.factories.CeldaModelFactory;

public class TableroModelImpl implements TableroModel {
    private static TableroModelImpl instance;

    private TableroController controller;
    private CeldaModelFactory celdaFactory;

    private CeldaModel[][] tableroCeldas;
    private int[][] tableroNumeros;
    

    private TableroModelImpl() {}

    public static TableroModel getInstance() {
        if (instance == null) {
            instance = new TableroModelImpl();
        }

        return instance;
    }

    @Override
    public void cargarTablero(int[][] tablero) {
        tableroNumeros = tablero;
        int rows = tableroNumeros.length;
        int columns = tableroNumeros[0].length;

        tableroCeldas = new CeldaModel[rows][columns];

        int valorActual;
        for(int fila = 0; fila < rows; fila++) {
            for(int col = 0; col < columns; col++) {
                valorActual = tableroNumeros[fila][col];

                tableroCeldas[fila][col] = celdaFactory.createCeldaModel(fila, col, valorActual);
            }
        }

    }

    @Override
    public void actualizarValorCelda(int posX, int posY, int valor) {
        tableroNumeros[posX][posY] = valor;
        tableroCeldas[posX][posY].actualizarValor(valor);
    }

    @Override
    public void actualizarSpriteCelda(int posX, int posY, int estado) {
        tableroCeldas[posX][posY].actualizarSprite(estado);
    }

    @Override
    public List<CeldaModel> actualizarSeleccionadas(int posX, int posY) {
        List<CeldaModel> celdas = new LinkedList<CeldaModel>();

        //Seleccionar fila
        //Seleccionar columna
        //Seleccionar panel


        return celdas;
    }

    @Override
    public void notificarCambioEnCelda(int posX, int posY) {
        List<CeldaModel> celdas = this.actualizarSeleccionadas(posX, posY);
        this.notificarCambios(celdas);
    }

    @Override
    public void notificarCambios(List<CeldaModel> celdas) {
        controller.notificarCambios(celdas);
    }

    @Override
    public void setController(TableroController controller) {
        this.controller = controller;

    }

    @Override
    public void setCeldaFactory(CeldaModelFactory factory) {
        celdaFactory = factory;
    }

}
