package main.model;

import java.util.List;

import main.controller.TableroController;
import main.model.factories.CeldaModelFactory;

public interface TableroModel {

    /**
     * Recibe una matriz de enteros y carga la misma en el estado interno del TableroModel.
     * A la vez que inicializa el tablero de CeldaModel el cual representa el mismo tablero.
     * @param tablero matriz de enteros.
     */
    public void cargarTablero(int[][] tablero);

    /**
     * Actualiza el valor de la celda ubicada en la posicion parametrizada por el valor parametrizado.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param valor nuevo valor que va a tomar la celda.
     */
    public void actualizarValorCelda(int posX, int posY, int valor);

    /**
     * Actualiza el sprite de la celda ubicada en la posicion parametrizada teniendo en cuenta el estado parametrizado.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param estado nuevo estado que indica el sprite que va a tomar la celda.
     */
    public void actualizarSpriteCelda(int posX, int posY, int estado);

    /**
     * Actualiza los sprites de toda la fila, columna y panel de la celda ubicada en la posicion parametrizada.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @return Una lista con las celdas las cuales recibieron cambios en su estado.
     */
    public List<CeldaModel> actualizarSeleccionadas(int posX, int posY);

    /**
     * Le notifica al TableroModel que ocurrio un cambio en el estado de la celda ubicada en la posicion parametrizada.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     */
    public void notificarCambioEnCelda(int posX, int posY);

    /**
     * Notifica al TableroController de los cambios en los estados de las celdas parametrizadas.
     * @param celdas Colleccion de celdas en las cuales se notificaron cambios.
     */
    public void notificarCambios(List<CeldaModel> celdas);

    /**
     * Establece el controller a utilizar.
     * @param controller Controller que usara el TableroModel.
     */
    public void setController(TableroController controller);

    /**
     * Establece el celdaFactory a utilizar.
     * @param controller Controller que usara el TableroModel.
     */
    public void setCeldaFactory(CeldaModelFactory factory);
}
