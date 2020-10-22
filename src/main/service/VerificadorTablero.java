package main.service;

public interface VerificadorTablero extends VerificadorTableroService {
    /**
     * Comprueba que no haya numeros repetidos en cada fila de la matriz
     * @param matriz Matriz a analizar
     * @return True en caso de que no se repitan numeros, falso en caso contrario.
     */
    public boolean verificarFilas(int[][] matriz);

    /**
     * Comprueba que no haya numeros repetidos en cada columna de la matriz
     * @param matriz Matriz a analizar
     * @return True en caso de que no se repitan numeros, falso en caso contrario.
     */
    public boolean verificarColumnas(int[][] matriz);

    /**
     * Comprueba que no haya numeros repetidos en cada panel de la matriz
     * @param matriz Matriz a analizar
     * @return True en caso de que no se repitan numeros, falso en caso contrario.
     */
    public boolean verificarPaneles(int[][] matriz);
}
