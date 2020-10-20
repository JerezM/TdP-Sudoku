package main.model.entidadesGraficas;

import javax.swing.ImageIcon;

public interface EntidadGrafica {

    /**
     * Actualiza el conjunto de sprites en base al nuevo valor parametrizado.
     * @param valor indica el conjunto de sprites.
     */
    public void actualizarValor(int valor);

    /**
     * Actualiza el sprite en base al estado parametrizado.
     * @param estado indica el estado del sprite solicitado.
     */
    public void actualizarSprite(int estado);
    
    /**
     * Consulta el sprite actual.
     * @return El sprite actual.
     */
    public ImageIcon getSpriteIcon();
}
