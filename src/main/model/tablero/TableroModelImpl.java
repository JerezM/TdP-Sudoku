package main.model.tablero;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import main.controller.tablero.TableroControllerImpl;
import main.controller.tablero.TableroControllerModel;
import main.model.celda.CeldaModel;
import main.model.celda.factories.CeldaModelFactory;
import main.model.timer.TimerModel;
import main.model.timer.TimerModelImpl;
import main.service.entry.*;
import main.service.EstadosPosiblesCeldas;
import main.service.verificador_tablero.VerificadorTableroService;
import main.service.verificador_tablero.VerificadorTableroServiceImpl;

public class TableroModelImpl implements TableroModelController, TableroModelCelda, TableroModel {
    private static TableroModelImpl instance;

    private TableroControllerModel controller;
    private CeldaModelFactory celdaFactory;
    
    private TimerModel timer;

    private CeldaModel[][] tableroCeldas;
    private int[][] tableroNumeros;
  //private int[][] tableroEstadoInicial; -- Se utilizara para reiniciar el tablero

    private Map<Integer, Entry<Integer, Integer>> paneles;

    private TableroModelImpl() {
    	this.controller = TableroControllerImpl.getInstance();
        this.controller.setTableroModel(this);
        
        this.timer = TimerModelImpl.getInstance();
    	
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
      //tableroEstadoInicial = tablero; -- proximo feature

        int rows = tableroNumeros.length;
        int columns = tableroNumeros[0].length;

        tableroCeldas = new CeldaModel[rows][columns];

        List<Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>>> celdas = new LinkedList<Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>>>();

        for(int fila = 0; fila < rows; fila++) {
            for(int col = 0; col < columns; col++) {
                int valorActual = tableroNumeros[fila][col];

                tableroCeldas[fila][col] = celdaFactory.createCeldaModel(col, fila, valorActual);

                Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>> celdaActual;
                boolean esInicial = ( tableroNumeros[fila][col] != 0);

                Entry<Entry<Integer, Integer>, ImageIcon> datosCeldaActual;
                Entry<Integer, Integer> coordenadasXYActual = new EntryImpl<Integer, Integer>(col, fila);
                ImageIcon spriteActual = tableroCeldas[fila][col].getSpriteIcon();
                datosCeldaActual = new EntryImpl<Entry<Integer,Integer>,ImageIcon>(coordenadasXYActual, spriteActual);

                celdaActual = new EntryImpl<Boolean,Entry<Entry<Integer,Integer>,ImageIcon>>(esInicial, datosCeldaActual);


                celdas.add(celdaActual);
            }
        }

        this.notificarInicioTablero(celdas);
        this.timer.iniciarTimer();
    }

    @Override
    public void verificarTablero() {
        VerificadorTableroService verificador = new VerificadorTableroServiceImpl();

        Entry<Boolean, List<Entry<Integer, Integer>>> e = verificador.verificarTablero(tableroNumeros);
        boolean cumple = e.getKey();

        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas;
        String tiempoTotal = "";//Se utilizara para indicar el tiempo trancurrido en caso de ganar la partida.

        if(!cumple){//En caso de ser incorrecto se encarga de marcar las celdas con errores.
            List<Entry<Integer, Integer>> listErrores = e.getValue();
            int estadoError = EstadosPosiblesCeldas.CELDA_EN_ERROR.getEstado();

            int noSelect = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();
            this.pintarTablero(noSelect);
            
            celdas = this.actualizarSpriteSeleccionadas(listErrores, estadoError);
        }
        else {//En caso de haber ganado el juego pinta todas las celdas como seleccionadas.
            int select = EstadosPosiblesCeldas.CELDA_SELECCIONADA.getEstado();
            celdas = this.pintarTablero(select);
            
            tiempoTotal = timer.detenerTimer();
        }
        
        this.notificarVerificacionTablero(cumple, celdas, tiempoTotal);
    }

    @Override
    public void actualizarValorCelda(int posX, int posY, int valor) {
        tableroNumeros[posY][posX] = valor;
        tableroCeldas[posY][posX].actualizarValor(valor);

        int noSelect = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdasActualizadas = this.pintarTablero(noSelect);

        this.notificarCambios(celdasActualizadas);
    }

    @Override
    public void actualizarSpriteCelda(int posX, int posY, int estado) {
        

        if ( estado == EstadosPosiblesCeldas.CELDA_SELECCIONADA.getEstado() ) {
            tableroCeldas[posY][posX].actualizarSprite(estado);
        }
        else if ( estado == EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado() ) {//Si la celda en cuestion fue deseleccionada entonces simplemente notifico la limpieza del tablero
            
            int noSelect = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();
            List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas = this.pintarTablero(noSelect);
            notificarCambios(celdas);
        }

    }

    @Override
    public void notificarCambioEstadoEnCelda(int posX, int posY, int estado) {
        int selectIndirect = EstadosPosiblesCeldas.CELDA_SELECCIONADA_INDIRECTAMENTE.getEstado();
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdasActualizadas = this.actualizarSpriteSeleccionadasIndirec(posX, posY, selectIndirect);
        
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
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas = new LinkedList<Entry<Entry<Integer, Integer>, ImageIcon>>();
        this.inicializarMedidasPaneles();

        int inicioPanelX = paneles.get(posX).getKey();
        int finPanelX = paneles.get(posX).getValue();
        int inicioPanelY = paneles.get(posY).getKey();
        int finPanelY = paneles.get(posY).getValue();

        //Seleccionar fila
        for (int i = 0; i < tableroCeldas[0].length; i++) {
            if (i < inicioPanelX || i > finPanelX) {

                boolean esCeldaSeleccionada = (i == posX);
                if (!esCeldaSeleccionada) {
                    tableroCeldas[posY][i].actualizarSpriteSeleccionada(estado);

                    Entry<Integer, Integer> celdaActualCoordenadas = new EntryImpl<Integer, Integer>(i, posY);
                    ImageIcon spriteCeldaActual = tableroCeldas[posY][i].getSpriteIcon();
                    Entry<Entry<Integer, Integer>, ImageIcon> datosCelda;
                    datosCelda = new EntryImpl<Entry<Integer,Integer>,ImageIcon>(celdaActualCoordenadas, spriteCeldaActual);

                    celdas.add(datosCelda);
                }
            }
        }

        //Seleccionar columna
        for (int j = 0; j < tableroCeldas.length; j++) {
            if (j < inicioPanelY || j > finPanelY) {
                
                boolean esCeldaSeleccionada = (j == posY);
                if (!esCeldaSeleccionada) {
                    tableroCeldas[j][posX].actualizarSpriteSeleccionada(estado);

                    Entry<Integer, Integer> celdaActualCoordenadas = new EntryImpl<Integer, Integer>(posX, j);
                    ImageIcon spriteCeldaActual = tableroCeldas[j][posX].getSpriteIcon();
                    Entry<Entry<Integer, Integer>, ImageIcon> datosCelda;
                    datosCelda = new EntryImpl<Entry<Integer,Integer>,ImageIcon>(celdaActualCoordenadas, spriteCeldaActual);

                    celdas.add(datosCelda);
                }
            }
        }

        //Seleccionar panel
        for (int k = inicioPanelX; k <= finPanelX; k++) {
            for (int l = inicioPanelY; l <= finPanelY; l++) {
                
                boolean esCeldaSeleccionada = ( (k == posX) && (l == posY) );
                if (!esCeldaSeleccionada) {
                    tableroCeldas[l][k].actualizarSpriteSeleccionada(estado);

                    Entry<Integer, Integer> celdaActualCoordenadas = new EntryImpl<Integer, Integer>(k, l);
                    ImageIcon spriteCeldaActual = tableroCeldas[l][k].getSpriteIcon();
                    Entry<Entry<Integer, Integer>, ImageIcon> datosCelda;
                    datosCelda = new EntryImpl<Entry<Integer,Integer>,ImageIcon>(celdaActualCoordenadas, spriteCeldaActual);

                    celdas.add(datosCelda);
                }
            }
        }

        //Deseleccionar las demas celdas
        int noSelect = EstadosPosiblesCeldas.CELDA_NO_SELECCIONADA.getEstado();
        for (int i = 0; i < tableroCeldas.length; i++) {
            for (int j = 0; j < tableroCeldas[0].length; j++) {
                boolean estaEnPanelEjeX = (j >= inicioPanelX && j <= finPanelX );//esta adentro del panel seleccionado con respecto al eje x
                boolean estaEnPanelEjeY = (i >= inicioPanelY && i <= finPanelY );//esta adentro del panel seleccionado con respecto al eje y
                boolean estaEnPanel = (estaEnPanelEjeX && estaEnPanelEjeY);
                boolean estaEnFilaPosX = (i == posY);//esta en la fila seleccionada
                boolean estaEnColPosY = (j == posX);//esta en la columna seleccionada
                
                if (!estaEnPanel && !estaEnFilaPosX && !estaEnColPosY) {//si no es una de las celdas seleccionadas
                   
                    tableroCeldas[i][j].actualizarSpriteSeleccionada(noSelect);

                    Entry<Integer, Integer> celdaActualCoordenadas = new EntryImpl<Integer, Integer>(j, i);
                    ImageIcon spriteCeldaActual = tableroCeldas[i][j].getSpriteIcon();
                    Entry<Entry<Integer, Integer>, ImageIcon> datosCelda;
                    datosCelda = new EntryImpl<Entry<Integer,Integer>,ImageIcon>(celdaActualCoordenadas, spriteCeldaActual);

                    celdas.add(datosCelda);
                }
            }
            
        }
        
        return celdas;
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
     * Pinta el tablero de cualquier estado posible que tengan las celdas y las establece a todas con el estado parametrizado.
     * @param estado Estado a establecer en todas las celdas.
     * @return Una lista de entradas las cuales contienen las coordenadas de las celdas seleccionadas,
     * junto con su respectivo sprite.
     */
    protected List<Entry<Entry<Integer, Integer>, ImageIcon>> pintarTablero(int estado) {
        List<Entry<Entry<Integer, Integer>, ImageIcon>> celdas = new LinkedList<Entry<Entry<Integer, Integer>, ImageIcon>>();

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
     * @param celdas colleccion de celdas con las coordenas de las celdas a actualizar.
     * @param tiempoTotal en caso de que el resultado sea true,
     * este string va a indicar la cantidad de tiempo que se tardo en resolver el tablero.
     * En caso de ser false, simplemente sera una string vacio.
     *  
     */
    protected void notificarVerificacionTablero(boolean resultado, List<Entry<Entry<Integer, Integer>,
                                                ImageIcon>> celdas, String tiempoTotal) {
        controller.notificarVerificacionTablero(resultado, celdas, tiempoTotal);
    }

    /**
     * Notifica al TableroController de la inicializacion exitosa del tablero.
     * @param celdas Colleccion de coordenadas de celdas en las cuales se notificaron cambios, con sus respectivos sprites.
     *  Vease: List< esInicial,< < posX, posY>, sprite > >.
     */
    public void notificarInicioTablero(List<Entry<Boolean, Entry<Entry<Integer, Integer>, ImageIcon>>> celdas) {
        controller.notificarInicioTablero(celdas);
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
