package main.view.tablero;

public interface TableroView {
    
    /**
     * Notifica al TableroController del cambio en el valor de la celda ubicada en la posicion parametrizada.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param valor nuevo valor que indica el nuevo sprite que va a tomar la celda.
     */
    public void actualizarValor(int valor, int posX, int posY);
}
