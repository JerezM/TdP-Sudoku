package main.model.tablero;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import main.controller.tablero.TableroControllerModel;
import main.model.celda.CeldaModel;
import main.model.celda.factories.CeldaModelFactory;
import main.service.entry.*;
import main.service.EstadosPosiblesCeldas;
import main.service.verificador_tablero.VerificadorTableroService;
import main.service.verificador_tablero.VerificadorTableroServiceImpl;

public class TableroModelImpl implements TableroModelController, TableroModelCelda, TableroModel {
    private static TableroModelImpl instance;

    private TableroControllerModel controller;
    private CeldaModelFactory celdaFactory;

    private CeldaModel[][] tableroCeldas;
    private int[][] tableroNumeros;
    private int[][] tableroEstadoInicial;

    private Map<Integer, Entry<Integer, Integer>> paneles;

    private TableroModelImpl() {
        this.inicializarMedidasPaneles();
    }

    public static TableroModelImpl getInstance() {
        if (instance == null) {
            instance = new TableroModelImpl();
        }

        return instance;
    }

    @Override
    public void cargarTablero(int[][] tablero) {
        tableroNumeros = tablero;
        tableroEstadoInicial = tablero;

        int rows = tableroNumeros.length;
        int columns = tableroNumeros[0].length;

        tableroCeldas = new CeldaModel[rows][columns];

        for(int fila = 0; fila < rows; fila++) {
            for(int col = 0; col < columns; col++) {
                int valorActual = tableroNumeros[fila][col];

                tableroCeldas[fila][col] = celdaFactory.createCeldaModel(col, fila, valorActual);
            }
        }

        
    }

    @Override
    public void verificarTablero() {
        VerificadorTableroService verificador = new VerificadorTableroServiceImpl();

        Entry<Boolean, List<Entry<Integer, Integer>>> e = verificador.verificarTablero(tableroNumeros, tableroEstadoInicial);
        boolean cumple = e.getKey();

        this.notificarVerificacionTablero(cumple);

        if(!cumple){//En caso de ser incorrecto se encarga de marcar las celdas con errores y notificar de las mismas al controller.
            List<Entry<Integer, Integer>> listErrores = e.getValue();
            int estadoError = EstadosPosiblesCeldas.CELDA_EN_ERROR.getEstado();

            this.limpiarTablero();
            List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas = this.actualizarSpriteSeleccionadas(listErrores, estadoError);
            this.notificarCambios(celdas);
        }
        
    }

    @Override
    public void actualizarValorCelda(int posX, int posY, int valor) {
        tableroNumeros[posY][posX] = valor;
        tableroCeldas[posY][posX].actualizarValor(valor);

        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdasActualizadas = this.limpiarTablero();

        this.notificarCambios(celdasActualizadas);
    }

    @Override
    public void actualizarSpriteCelda(int posX, int posY, int estado) {
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas = this.limpiarTablero();

        if ( estado == EstadosPosiblesCeldas.CELDA_SELECCIONADA.getEstado() ) {
            tableroCeldas[posY][posX].actualizarSprite(estado);
        }
        else {//Si la celda en cuestion fue deseleccionada entonces simplemente notifico la limpieza del tablero
            notificarCambios(celdas);
        }

    }

    @Override
    public void notificarCambioEstadoEnCelda(int posX, int posY, int estado) {
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdasActualizadas = this.actualizarSpriteSeleccionadasIndirec(posX, posY, estado);

        ImageIcon spriteCelda = (tableroCeldas[posY][posX]).getSpriteIcon();
        Entry<Integer, Integer> coordenadasXY = new EntryImpl<Integer, Integer>(posX, posY);
        celdasActualizadas.add( new EntryImpl<Entry<Integer, Integer>, ImageIcon>(coordenadasXY, spriteCelda) );

        this.notificarCambios(celdasActualizadas);
    }

    /**
     * Actualiza los sprites de toda la fila, columna y panel de la celda ubicada en la posicion parametrizada.
     * @param posX Posicion en el eje X de la celda en cuestion.
     * @param posY Posicion en el eje X de la celda en cuestion.
     * @param estado Estado que recibiran las celdas. 
     * @return Una lista con las coordenadas de las celdas las cuales recibieron cambios en su estado, junto con su nuevo sprite.
     */
    protected List<Entry<Entry<Integer, Integer>, ImageIcon>> actualizarSpriteSeleccionadasIndirec(int posX, int posY, int estado) {
        List<Entry<Integer, Integer>> celdas = new LinkedList<Entry<Integer, Integer>>();
        this.inicializarMedidasPaneles();

        int inicioPanelX = paneles.get(posX).getKey();
        int finPanelX = paneles.get(posX).getValue();
        int inicioPanelY = paneles.get(posY).getKey();
        int finPanelY = paneles.get(posY).getValue();

        //Seleccionar fila
        for (int i = 0; i < tableroCeldas[0].length; i++) {
            if (i < inicioPanelX || i > finPanelX) {

                Entry<Integer, Integer> celdaActualCoordenadas = new EntryImpl<Integer, Integer>(i, posY);
                celdas.add(celdaActualCoordenadas);
            }
        }

        //Seleccionar columna
        for (int j = 0; j < tableroCeldas.length; j++) {
            if (j < inicioPanelY || j > finPanelY) {
                
                Entry<Integer, Integer> celdaActualCoordenadas = new EntryImpl<Integer, Integer>(posX, j);
                celdas.add(celdaActualCoordenadas);
            }
        }

        //Seleccionar panel
        for (int k = inicioPanelX; k <= finPanelX; k++) {
            for (int l = inicioPanelY; l <= finPanelY; l++) {
                
                Entry<Integer, Integer> celdaActualCoordenadas = new EntryImpl<Integer, Integer>(k, l);
                celdas.add(celdaActualCoordenadas);
            }
        }
        
        return this.actualizarSpriteSeleccionadas(celdas, estado);
    }

    /**
     * Actualiza las celdas parametrizadas con el estado indicado.
     * @param celdas Lista de coordenadas con las celdas a modificar.
     * @param estado Estado a establecer en las celdas indicadas.
     * @return Una lista de entradas las cuales contienen las coordenadas de las celdas seleccionadas,
     * junto con su respectivo sprite.
     */
    protected List<Entry<Entry<Integer, Integer>, ImageIcon>> actualizarSpriteSeleccionadas(List<Entry<Integer, Integer>> celdas, int estado){
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdasActualizadas = new LinkedList<Entry<Entry<Integer, Integer>, ImageIcon>>();
        
        for (Entry<Integer, Integer> coordenadas : celdas) {
            int posX = coordenadas.getKey();
            int posY = coordenadas.getValue();

            tableroCeldas[posY][posX].actualizarSpriteSeleccionada(estado);

            ImageIcon sprite =  tableroCeldas[posY][posX].getSpriteIcon();

            celdasActualizadas.add( new EntryImpl<Entry<Integer, Integer>, ImageIcon>(coordenadas, sprite) );
        }

        return celdasActualizadas;
    }

    /**
     * Limpia el tablero de cualquier estado posible que tengan las celdas y las establece a todas como no seleccionadas.
     * @return Una lista de entradas las cuales contienen las coordenadas de las celdas seleccionadas,
     * junto con su respectivo sprite.
     */
    protected List<Entry<Entry<Integer, Integer>, ImageIcon>> limpiarTablero() {
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas = new LinkedList<Entry<Entry<Integer, Integer>, ImageIcon>>();
        int estado = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();

        for (int posY = 0; posY < tableroCeldas.length; posY++) {
            for (int posX = 0; posX < tableroCeldas[0].length; posX++) {
                tableroCeldas[posY][posX].actualizarSpriteSeleccionada(estado);

                Entry<Entry<Integer, Integer>, ImageIcon> celdaActual;
                Entry<Integer, Integer> coordenadasXY = new EntryImpl<Integer, Integer>(posX, posY);
                ImageIcon spriteActual = tableroCeldas[posY][posX].getSpriteIcon();

                celdaActual = new EntryImpl<Entry<Integer, Integer>, ImageIcon>(coordenadasXY, spriteActual);

                celdas.add(celdaActual);
            }
        }

        return celdas;
    }

    /**
     * Notifica al TableroController de los cambios en los estados de las celdas parametrizadas.
     * @param celdas Colleccion de celdas en las cuales se notificaron cambios.
     */
    protected void notificarCambios(List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas) {
        controller.notificarCambios(celdas);
    }

    /**
     * Le notifica al tableroController de si el tablero actual se encuentra en un estado ganador, o si contiene errores.
     * @param resultado True en caso de ser un tablero completo y correcto, Falso en caso de contener errores.
     */
    protected void notificarVerificacionTablero(boolean resultado) {
        controller.notificarVerificacionTablero(resultado);
    }

    @Override
    public void setController(TableroControllerModel controller) {
        this.controller = controller;

    }

    @Override
    public void setCeldaFactory(CeldaModelFactory factory) {
        celdaFactory = factory;
    }

    /**
     * Se encarga de llenar el mapeo de paneles con las coordenadas de los mismos.
     */
    protected void inicializarMedidasPaneles(){
        paneles = new HashMap<Integer, Entry<Integer, Integer>>();

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
}
