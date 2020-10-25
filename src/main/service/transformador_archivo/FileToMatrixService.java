package main.service.transformador_archivo;

import java.io.File;

import main.exception.SudokuFileException;

public interface FileToMatrixService {

    /**
     * Transforma el contenido del archivo en una matriz de enteros que representa un tablero de Sudoku.
     * @param file Archivo a leer.
     * @return Matriz de enteros que representa el estado interno contenido en el archivo.
     */
    public int[][] transform(File file) throws SudokuFileException;
}
