package main.service.verificador_tablero;

import java.util.List;

import main.service.entry.Entry;

public interface VerificadorTableroService {
    
    /**
     * Consulta si el tablero de sudoku parametrizado esta completo correctamente.
     * @param matrizActual Matriz de enteros que simula el estado actual del tablero de sudoku.
     * @return Una entrada de < Boolean, List< Entry< posX, posY>>,
     *  la cual indica true en caso de que el tablero esta completo correctamente junto con una lista vacia,
     *  y en caso de ser falso la misma lista contendra todas las coordenadas de celdas en las cuales se encuentren errores.
     */
    public Entry<Boolean, List<Entry<Integer, Integer>>> verificarTablero(int[][] matrizActual);
}
