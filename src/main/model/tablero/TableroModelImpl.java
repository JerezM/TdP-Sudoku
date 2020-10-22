package main.model.tablero;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.controller.tablero.TableroControllerModel;
import main.model.CeldaModel;
import main.model.factories.CeldaModelFactory;
import main.service.EntryImpl;
import main.service.VerificadorTableroService;
import main.service.VerificadorTableroServiceImpl;

public class TableroModelImpl implements TableroModelController, TableroModelCelda, TableroModel {
    private static TableroModelImpl instance;

    private TableroControllerModel controller;
    private CeldaModelFactory celdaFactory;

    private CeldaModel[][] tableroCeldas;
    private int[][] tableroNumeros;

    private Map<Integer, Entry<Integer, Integer>> paneles;
    

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
    public boolean verificarTablero() {
        VerificadorTableroService verificador = new VerificadorTableroServiceImpl();

        boolean esCorrecto = verificador.verificarTablero(tableroNumeros);

        return esCorrecto;
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
        this.inicializarMedidasPaneles();

        int inicioPanelX = paneles.get(posX).getKey();
        int finPanelX = paneles.get(posX).getValue();
        int inicioPanelY = paneles.get(posY).getKey();
        int finPanelY = paneles.get(posY).getValue();

        //Seleccionar fila
        for (int i = 0; i < tableroCeldas.length; i++) {
            if (i < inicioPanelX || i > finPanelX) {
                CeldaModel celdaActual = tableroCeldas[i][posY];
                celdaActual.actualizarSpriteSeleccionada();

                celdas.add(celdaActual);
            }
        }

        //Seleccionar columna
        for (int j = 0; j < tableroCeldas[0].length; j++) {
            if (j < inicioPanelY || j > finPanelY) {
                CeldaModel celdaActual = tableroCeldas[posX][j];
                celdaActual.actualizarSpriteSeleccionada();

                celdas.add(celdaActual);
            }
        }

        //Seleccionar panel
        for (int k = inicioPanelX; k <= finPanelX; k++) {
            for (int l = inicioPanelY; l <= finPanelY; l++) {
                CeldaModel celdaActual = tableroCeldas[k][l];
                celdaActual.actualizarSpriteSeleccionada();

                celdas.add(celdaActual);
            }
        }

        return celdas;
    }

    /**
     * Se encarga de llenar el mapeo de paneles con las coordenadas de los mismos.
     */
    protected void inicializarMedidasPaneles(){
        paneles.put(0, new EntryImpl<Integer, Integer>(0, 2));
        paneles.put(1, new EntryImpl<Integer, Integer>(0, 2));
        paneles.put(2, new EntryImpl<Integer, Integer>(0, 2));
        paneles.put(3, new EntryImpl<Integer, Integer>(3, 5));
        paneles.put(4, new EntryImpl<Integer, Integer>(3, 5));
        paneles.put(5, new EntryImpl<Integer, Integer>(3, 5));
        paneles.put(6, new EntryImpl<Integer, Integer>(6, 8));
        paneles.put(7, new EntryImpl<Integer, Integer>(6, 8));
        paneles.put(8, new EntryImpl<Integer, Integer>(6, 8));
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
