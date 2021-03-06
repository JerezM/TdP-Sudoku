package main.service.verificador_tablero;

import java.util.List;

import main.service.entry.Entry;

public interface VerificadorTablero extends VerificadorTableroService {
    /**
     * Comprueba que no haya numeros repetidos en cada fila de la matriz
     * @param matrizActual Matriz a analizar.
     * @return Una entrada de < Boolean, List< Entry< posX, posY>>,
     *  la cual indica true en caso de que el tablero esta completo correctamente junto con una lista vacia,
     *  y en caso de ser falso la misma lista contendra todas las coordenadas de celdas en las cuales se encuentren errores.
     */
    public Entry<Boolean, List<Entry<Integer, Integer>>> verificarFilas(int[][] matrizActual);

    /**
     * Comprueba que no haya numeros repetidos en cada columna de la matriz
     * @param matrizActual Matriz a analizar.
     * @return Una entrada de < Boolean, List< Entry< posX, posY>>,
     *  la cual indica true en caso de que el tablero esta completo correctamente junto con una lista vacia,
     *  y en caso de ser falso la misma lista contendra todas las coordenadas de celdas en las cuales se encuentren errores.
     */
    public Entry<Boolean, List<Entry<Integer, Integer>>> verificarColumnas(int[][] matrizActual);

    /**
     * Comprueba que no haya numeros repetidos en cada panel de la matriz
     * @param matrizActual Matriz a analizar.
     * @return Una entrada de < Boolean, List< Entry< posX, posY>>,
     *  la cual indica true en caso de que el tablero esta completo correctamente junto con una lista vacia,
     *  y en caso de ser falso la misma lista contendra todas las coordenadas de celdas en las cuales se encuentren errores.
     */
    public Entry<Boolean, List<Entry<Integer, Integer>>> verificarPaneles(int[][] matrizActual);
}
