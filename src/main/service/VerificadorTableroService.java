package main.service;

public interface VerificadorTableroService {
    
    /**
     * Consulta si el tablero de sudoku parametrizado esta completo correctamente.
     * @param matriz Matriz de enteros que simula el estado de un tablero de sudoku.
     * @return True en caso de que el tablero este completo correctamente, falso en caso contrario.
     */
    public boolean verificarTablero(int[][] matriz);
}
