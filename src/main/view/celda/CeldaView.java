package main.view.celda;

public interface CeldaView extends CeldaViewTablero {

    /**
     * Notifica al TableroView de que dicha celda fue seleccionada/deseleccionada.
     */
    public void actualizarSprite();
}
