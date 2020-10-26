package main.view.tablero;

public interface TableroView {
    
    /**
     * Notifica al TableroController del cambio en el valor de la celda ubicada en la posicion parametrizada.
     * @param valor nuevo valor que indica el nuevo sprite que va a tomar la celda.
     */
    public void actualizarValor(int valor);
}
