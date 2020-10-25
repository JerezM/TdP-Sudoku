package main.service.generador_sudoku;

public interface SudokuGeneratorService {
    
    /**
     * Transforma la tablero recibido, el cual es uno completo, en un tablero incompleto
     * en el cual se sea capaz de jugar.
     * @param matriz Matriz la cual se va a tomar como semilla.
     * @return Matriz incompleta la cual se puede utilizar como tablero jugable.
     */
    public int[][] generateSudoku(int[][] matriz);
}
