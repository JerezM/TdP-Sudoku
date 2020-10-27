package main.view.tablero;

public interface TableroViewOpciones {
    
    /**
     * Notifica al TableroController del cambio en el valor de la celda ubicada en la posicion parametrizada.
     * @param valor nuevo valor que indica el nuevo sprite que va a tomar la celda.
     */
    public void actualizarValor(int valor);

    /**
     * Consulta si hay una celda seleccionada actualmente.
     * @return true si hay una celda seleccionada, de no ser asi retorna false.
     */
    public boolean hayCeldaSeleccionada();
}
