package main.view.tablero;

public interface TableroViewCelda {
    
   /**
     * Notifica al TableroController del cambio en el estado de la celda ubicada en la posicion parametrizada.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param estado nuevo estado que indica el sprite que va a tomar la celda.
     */
    public void actualizarSprite(int estado, int posX, int posY);
}
