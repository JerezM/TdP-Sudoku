package main.controller;

import java.util.List;

import main.exception.SudokuFileException;
import main.model.CeldaModel;


public interface TableroController {

    /**
     * Se encarga de cargar el tablero contenido en el archivo parametrizado al TableroModel.
     * @throws SudokuFileException Se lanza si el archivo no cumple la sintaxis o el tablero interno es incorrecto.
     */
    public void cargarTableroDesdeArchivo(/*Le pasa el file */) throws SudokuFileException;

    /**
     * Recibe un archivo el cual contiene la representacion del tablero y lo transforma en una matriz de enteros.
     * @return Una matriz de entero que representa un tablero de sudoku completo.
     * @throws SudokuFileException Se lanza si el archivo no cumple la sintaxis o el tablero interno es incorrecto.
     */
    public int[][] fileToMatrix(/*Le pasa el file */) throws SudokuFileException;

    /**
     * Recibe una matriz que representa un tablero completo y la procesa en una matriz incompleta en la cual es posible jugar.
     * @param tableroCompleto Matriz a procesar.
     * @return Una matriz que representa un tablero incompleto.
     */
    public int[][] sudokuGenerator(int[][] tableroCompleto);

    /**
     * Actualiza el valor de la celda ubicada en la posicion parametrizada por el valor parametrizado.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param valor nuevo valor que va a tomar la celda.
     */
    public void actualizarValor(int valor, int posX, int posY);

    /**
     * Actualiza el valor de la celda ubicada en la posicion parametrizada por el valor parametrizado.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param estado nuevo estado que indica el sprite que va a tomar la celda.
     */
    public void actualizarSprite(int estado, int posX, int posY);

    /**
     * Notifica al TableroView de los cambios en los estados de las celdas parametrizadas.
     * @param celdas Colleccion de celdas en las cuales se notificaron cambios.
     */
    public void notificarCambios(List<CeldaModel> celdas);

    /**
     * Le consulta al TableroModel si el mismo contiene algun error o si esta completo satisfactoriamente.
     */
    public void verificarTablero();
}
