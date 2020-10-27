package main.model.timer.entidad_grafica;

import javax.swing.ImageIcon;

public interface EntidadGraficaTimer {
    
    /**
     * Solicita un arreglo de sprites que representan los valores parametrizados.
     * @return Un array lleno con 8 ImageIcon representado los valores parametrizados
     * de la siguiente manera: [h1, h2, :, m1, m2, :, s1, s2].
     */
    public ImageIcon[] getSpriteIcons(int h1, int h2, int m1, int m2, int s1, int s2);
}
