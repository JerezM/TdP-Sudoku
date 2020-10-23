package main.service.verificador_tablero;

public interface VerificadorTablero extends VerificadorTableroService {
    /**
     * Comprueba que no haya numeros repetidos en cada fila de la matriz
     * @param matrizActual Matriz a analizar.
     * @param matrizInicial Matriz inicial.
     * @return True en caso de que no se repitan numeros, falso en caso contrario.
     */
    public boolean verificarFilas(int[][] matrizActual, int[][] matrizInicial);

    /**
     * Comprueba que no haya numeros repetidos en cada columna de la matriz
     * @param matrizActual Matriz a analizar.
     * @param matrizInicial Matriz inicial.
     * @return True en caso de que no se repitan numeros, falso en caso contrario.
     */
    public boolean verificarColumnas(int[][] matrizActual, int[][] matrizInicial);

    /**
     * Comprueba que no haya numeros repetidos en cada panel de la matriz
     * @param matrizActual Matriz a analizar.
     * @param matrizInicial Matriz inicial.
     * @return True en caso de que no se repitan numeros, falso en caso contrario.
     */
    public boolean verificarPaneles(int[][] matrizActual, int[][] matrizInicial);
}
