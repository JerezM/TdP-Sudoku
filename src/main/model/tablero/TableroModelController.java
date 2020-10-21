package main.model.tablero;

public interface TableroModelController {

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
     * Comprueba si el tablero posee un estado completo y correcto.
     */
    public void verificarTablero();
    
}
