package main.service.transformador_archivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import main.exception.SudokuFileException;

public class FileToMatrixServiceImpl implements FileToMatrixService {

    @Override
    public int[][] transform(File file) throws SudokuFileException {
        int[][] matrizGenerada = new int[9][9];
        boolean cumpleFormato = true;

        try {

            InputStream in = new FileInputStream(file);
            Scanner scn = new Scanner(in);

            //Recorro linea por linea del archivo y voy completando la matriz mientras el archivo tenga contenido
            //y se cumpla el formato.
            for(int posY = 0; posY < matrizGenerada.length && ( cumpleFormato && scn.hasNextLine() ); posY++) {
                String line = scn.nextLine();
                cumpleFormato = line.matches("([1-9]\s[1-9]\s){4}[1-9]"); 

                for(int posX = 0; posX < matrizGenerada[0].length && cumpleFormato; posX++) {
                    
                    line = line.replaceAll("\s", "");
                    char actualChar = line.charAt(posX);
                    int actualCharValue = Character.getNumericValue(actualChar);

                    matrizGenerada[posY][posX] = actualCharValue;
                    
                }
            }

            if (!cumpleFormato) {
                throw new SudokuFileException("El archivo seleccionado no cumple con el formato indicado.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new SudokuFileException("Archivo invalido.");
        }



        return matrizGenerada;
    }
    
}
