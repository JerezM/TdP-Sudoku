package main.model.tablero;

public interface TableroModelCelda {

    /**
     * Le notifica al TableroModel que ocurrio un cambio en el estado de la celda ubicada en la posicion parametrizada.
     * @param posX posicion en el eje X de la celda en cuestion.
     * @param posY posicion en el eje X de la celda en cuestion.
     * @param estado indica el estado nuevo que tomo la celda en la posicion indicada.
     */
    public void notificarCambioEnCelda(int posX, int posY, int estado);

}
