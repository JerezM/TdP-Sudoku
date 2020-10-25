package main.exception;

@SuppressWarnings("serial")
public class SudokuFileException extends Exception {

    /**
     * Modela una exception cuando la sintaxis o el tablero del archivo es incorrecto.
     * @param msg Mensaje que se mostrara por consola.
     */
    public SudokuFileException(String msg) {
        super(msg);
    }
    
}
