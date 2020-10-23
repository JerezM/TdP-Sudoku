package main.service.verificador_tablero;

import java.util.List;
import java.util.Map.Entry;

public interface VerificadorTableroService {
    
    /**
     * Consulta si el tablero de sudoku parametrizado esta completo correctamente.
     * @param matrizActual Matriz de enteros que simula el estado actual del tablero de sudoku.
     * @param matrizInicial Matriz de enteros que simula el estado inicial del tablero de sudoku.
     * @return Una entrada de < Boolean, List< Entry< posX, posY>>,
     *  la cual indica true en caso de que el tablero esta completo correctamente junto con una lista vacia,
     *  y en caso de ser falso la misma lista contendra todas las coordenadas de celdas en las cuales se encuentren errores.
     *  obs: la misma no contendra las coordenadas de las celdas que tenga la matriz en el estado inicial.
     */
    public Entry<Boolean, List<Entry<Integer, Integer>>> verificarTablero(int[][] matrizActual, int[][] matrizInicial);
}
