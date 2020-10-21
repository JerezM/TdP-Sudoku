package main.model.tablero;

import java.util.LinkedList;
import java.util.List;

import main.controller.TableroControllerModel;
import main.model.CeldaModel;
import main.model.factories.CeldaModelFactory;

public class TableroModelImpl implements TableroModelController, TableroModelCelda, TableroModel {
    private static TableroModelImpl instance;

    private TableroControllerModel controller;
    private CeldaModelFactory celdaFactory;

    private CeldaModel[][] tableroCeldas;
    private int[][] tableroNumeros;
    

    private TableroModelImpl() {}

    public static TableroModelImpl getInstance() {
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
    public void verificarTablero() {

    }

    @Override
    public void notificarCambioEnCelda(int posX, int posY) {
        List<CeldaModel> celdas = this.actualizarSeleccionadas(posX, posY);
        this.notificarCambios(celdas);
    }

    /**
     * Actualiza los sprites de toda la fila, columna y panel de la celda ubicada en la posicion parametrizada.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @return Una lista con las celdas las cuales recibieron cambios en su estado.
     */
    protected List<CeldaModel> actualizarSeleccionadas(int posX, int posY) {
        List<CeldaModel> celdas = new LinkedList<CeldaModel>();

        //Seleccionar fila
        //Seleccionar columna
        //Seleccionar panel


        return celdas;
    }

    /**
     * Notifica al TableroController de los cambios en los estados de las celdas parametrizadas.
     * @param celdas Colleccion de celdas en las cuales se notificaron cambios.
     */
    protected void notificarCambios(List<CeldaModel> celdas) {
        controller.notificarCambios(celdas);
    }

    @Override
    public void setController(TableroControllerModel controller) {
        this.controller = controller;

    }

    @Override
    public void setCeldaFactory(CeldaModelFactory factory) {
        celdaFactory = factory;
    }

}
