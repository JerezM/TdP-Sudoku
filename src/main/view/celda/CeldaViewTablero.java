package main.view.celda;

import javax.swing.ImageIcon;

public interface CeldaViewTablero {

    /**
     * Actualiza el sprite actual de la celda por el parametrizado.
     * @param sprite Nuevo sprite.
     */
    public void notificarCambio(ImageIcon sprite);

    /**
     * Actualiza su estado y deje de estar seleccionada.
     * @param noSelect True esta seleccionada, False no esta seleccionada.
     */
    public void deseleccionar();

    /**
     * Consulta el la posicion en el eje x de la celda.
     * @return Posicion de la celda en el eje x.
     */
    public int getPosX();

    /**
     * Consulta el la posicion en el eje y de la celda.
     * @return Posicion de la celda en el eje y.
     */
    public int getPosY();
}
