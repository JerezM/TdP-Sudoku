package main.model.celda;

import javax.swing.ImageIcon;

/**
 * Interfaz para la clase CeldaModel, se encarga de representar la logica de cada celda del sudoku.
 * @author Martin Jerez
 */
public interface CeldaModel {

    /**
     * Actualiza el valor actual de la celda por el valor parametrizado.
     * @param valorNuevo Nuevo valor que va a tener la celda.
     */
    public void actualizarValor(int valorNuevo);

    /**
     * Actualiza el estado actual de la celda por el valor parametrizado, lo cual se ve reflejado en el sprite.
     * @param estadoNuevo Nuevo estado que va a tener la celda.
     */
    public void actualizarSprite(int estadoNuevo);

    /**
     * Actualiza el estado actual de la celda para demostrar que fue seleccionada indirectamente,
     * el cual se ve reflejado en el sprite.
     */
    public void actualizarSpriteSeleccionada(int estado); 

    /**
     * Consulta el sprite actual de la celda.
     * @return Sprite que representa el estado interno de la celda.
     */
	public ImageIcon getSpriteIcon();

    /**
     * Consulta el valor actual de la celda.
     * @return Valor actual de la celda.
     */
	public int getValor();
}
