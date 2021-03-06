package main.controller.tablero;

import java.io.File;

import main.exception.SudokuFileException;
import main.view.tablero.TableroViewController;

public interface TableroControllerView {

    /**
     * Se encarga de cargar el tablero contenido en el archivo parametrizado al TableroModel.
     * @throws SudokuFileException Se lanza si el archivo no cumple la sintaxis o el tablero interno es incorrecto.
     */
    public void cargarTableroDesdeArchivo(File file) throws SudokuFileException;

    /**
     * Actualiza el valor de la celda ubicada en la posicion parametrizada por el valor parametrizado.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param valor nuevo valor que va a tomar la celda.
     */
    public void actualizarValor(int valor, int posX, int posY);

    /**
     * Actualiza el estado de la celda ubicada en la posicion parametrizada por el valor parametrizado.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param estado nuevo estado que indica el sprite que va a tomar la celda.
     */
    public void actualizarSprite(int estado, int posX, int posY);

    /**
     * Le consulta al TableroModel si el mismo contiene algun error o si esta completo satisfactoriamente.
     */
    public void verificarTablero();
    
    /**
     * Establece el tableroView con el que se comunicara el controller.
     * @param tableroView tableroView que utilizara el controller.
     */
    public void setTableroView(TableroViewController tableroView);
}
