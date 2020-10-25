package main.view.celda;

import javax.swing.ImageIcon;

public interface CeldaViewTablero {

    /**
     * Actualiza el sprite actual de la celda por el parametrizado.
     * @param sprite Nuevo sprite.
     */
    public void notificarCambio(ImageIcon sprite);
}
