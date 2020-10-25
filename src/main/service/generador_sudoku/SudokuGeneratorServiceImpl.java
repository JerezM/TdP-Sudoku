package main.service.generador_sudoku;

import java.util.Random;

public class SudokuGeneratorServiceImpl implements SudokuGeneratorService {

    @Override
    public int[][] generateSudoku(int[][] matriz) {
        int cantCeldasFijas = 45;
        int cantColumnas = matriz[0].length;
        int cantCeldasABorrarPorFila = cantColumnas - (cantCeldasFijas / cantColumnas);

        int[] numberArray = new int[cantColumnas];
        for (int i = 0; i < numberArray.length; i++) {//Lleno el array con todas las posibles posiciones de una fila
            numberArray[i] = i;
        }

        for(int posY = 0; posY < matriz.length; posY++) {

            this.randomizeArray(numberArray);

            for(int index = 0; index < cantCeldasABorrarPorFila; index++) {
                int posX = numberArray[index];
                matriz[posY][posX] = 0;//vacio la celda
            }
            
        }
        

        return matriz;
    }

    /**
     * Se encarga de mezclar los elementos del array parametrizado.
     * @param arr Arreglo a mezclar.
     */
    protected void randomizeArray(int[] arr) {
        Random rand = new Random();
        int n = arr.length;

        for (int i = (n - 1) ; i > 0; i--) {
            
            int index = rand.nextInt(i);//Agarro un numero random entre 0 e i

            int temp = arr[i];//Cambio posiciones
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
    
}
